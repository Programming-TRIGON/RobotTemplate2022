package frc.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotConstants.SwerveConstants;

/**
 * The Swerve subsystem.
 */
public class SwerveSS extends SubsystemBase {
    private final SwerveDriveOdometry swerveOdometry;
    private final SwerveModule[] mSwerveMods;
    private final PigeonIMU gyro;

    public SwerveSS() {
        gyro = new PigeonIMU(new TalonSRX(SwerveConstants.PIGEON_ID));
        gyro.configFactoryDefault();
        zeroGyro();

        swerveOdometry = new SwerveDriveOdometry(SwerveConstants.SWERVE_KINEMATICS, getYaw());

        mSwerveMods = new SwerveModule[4];
        for(int i = 0; i < 4; i++) {
            mSwerveMods[i] = new SwerveModule(SwerveConstants.MODULES[i]);
        }
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
        SwerveModuleState[] swerveModuleStates = SwerveConstants.SWERVE_KINEMATICS.toSwerveModuleStates(
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
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, SwerveConstants.MAX_SPEED);

        // if we are not moving or rotating at all, set the desired state to the current state
        if(translation.getNorm() == 0 && rotation == 0) {
            for(SwerveModule mod : mSwerveMods) {
                swerveModuleStates[mod.moduleNumber].angle = mod.getState().angle;
            }
        }

        // set the desired state for each module
        for(SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
        }
    }

    /**
     * Sets each module by the given array
     *
     * @param desiredStates array of length 4, where each element is the desired state of the module
     */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.normalizeWheelSpeeds(desiredStates, SwerveConstants.MAX_SPEED);

        for(SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
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
        for(SwerveModule mod : mSwerveMods) {
            states[mod.moduleNumber] = mod.getState();
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
        return (SwerveConstants.INVERT_GYRO) ? Rotation2d.fromDegrees(360 - ypr[0]) : Rotation2d.fromDegrees(ypr[0]);
    }

    @Override
    public void periodic() {
        // update the odometry with the angle and the current state of each module
        swerveOdometry.update(getYaw(), getStates());
    }
}