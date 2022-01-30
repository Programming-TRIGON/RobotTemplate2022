package frc.robot.subsystems.swerve;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotConstants;
import frc.robot.utilities.Modules;

import static frc.robot.constants.RobotConstants.swerveConstants;

/**
 * The Swerve subsystem.
 */
public class SwerveSS extends SubsystemBase {
    private final SwerveDriveOdometry swerveOdometry;
    private final SwerveModule[] swerveModules;
    private final PigeonIMU gyro;

    public SwerveSS() {
        gyro = swerveConstants.swerveComponents.pigeon;
        gyro.configFactoryDefault();
        zeroGyro();

        swerveOdometry = new SwerveDriveOdometry(swerveConstants.SWERVE_KINEMATICS, getYaw());

        swerveModules = new SwerveModule[4];
        swerveModules[Modules.FRONT_LEFT.getId()] = new SwerveModule(swerveConstants.FRONT_LEFT_CONSTANTS);
        swerveModules[Modules.FRONT_RIGHT.getId()] = new SwerveModule(swerveConstants.FRONT_RIGHT_CONSTANTS);
        swerveModules[Modules.REAR_LEFT.getId()] = new SwerveModule(swerveConstants.REAR_LEFT_CONSTANTS);
        swerveModules[Modules.REAR_RIGHT.getId()] = new SwerveModule(swerveConstants.REAR_RIGHT_CONSTANTS);
    }

    /**
     * Drives the swerve by the given values
     *
     * @param translation   The translation to apply, where x is forward and y is to the left
     * @param rotation      The rotation to apply, in radians per second
     * @param fieldRelative Whether the translation and rotation are field-relative
     * @param isOpenLoop    Whether we should drive the modules in open loop, or in closed loop, with a PID loop for
     *                      each module's speed
     */
    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        // set the desired states based on the given
        // translation and rotation
        SwerveModuleState[] swerveModuleStates = swerveConstants.SWERVE_KINEMATICS.toSwerveModuleStates(
                fieldRelative ?
                ChassisSpeeds.fromFieldRelativeSpeeds( // if field relative, convert to field relative speeds
                        translation.getX(),
                        translation.getY(),
                        rotation,
                        getYaw()
                ) :
                new ChassisSpeeds( // if not field relative, just use the given translation and rotation
                        translation.getX(),
                        translation.getY(),
                        rotation
                )
        );
        // making sure the speeds are within the max speed
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, swerveConstants.MAX_SPEED);

        // if we are not moving or rotating at all, set the desired angle to the current angle
        if(translation.getNorm() == 0 && rotation == 0) {
            for(int i = 0; i < swerveModules.length; i++) {
                swerveModuleStates[i].angle = swerveModules[i].getAngle();
            }
        }

        // set the desired state for each module
        for(int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].setDesiredState(swerveModuleStates[i], isOpenLoop);
        }
    }

    /**
     * Sets each module by the given array
     *
     * @param desiredStates array of length 4, where each element is the desired state of the module
     */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.normalizeWheelSpeeds(desiredStates, swerveConstants.MAX_SPEED);

        for(int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].setDesiredState(desiredStates[i], true);
        }
    }

    /**
     * Returns the current pose of the robot as reported by the odometry
     *
     * @return the current pose of the robot as reported by the odometry
     */
    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    /**
     * Resets the odometry to the given pose
     *
     * @param pose the pose to reset to
     */
    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(pose, getYaw());
    }

    /**
     * Returns the current state of each module
     *
     * @return the current state of each module
     */
    public SwerveModuleState[] getStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(int i = 0; i < swerveModules.length; i++) {
            states[i] = swerveModules[i].getState();
        }
        return states;
    }

    /**
     * resets the gyro to 0
     */
    public void zeroGyro() {
        gyro.setYaw(0);
    }

    /**
     * Returns the current yaw (angle) of the robot
     *
     * @return the current yaw of the robot
     */
    public Rotation2d getYaw() {
        double[] ypr = new double[3];
        gyro.getYawPitchRoll(ypr);
        return (swerveConstants.INVERT_GYRO) ? Rotation2d.fromDegrees(360 - ypr[0]) : Rotation2d.fromDegrees(ypr[0]);
    }

    @Override
    public void periodic() {
        // update the odometry with the angle and the current state of each module
        swerveOdometry.update(getYaw(), getStates());
    }
}