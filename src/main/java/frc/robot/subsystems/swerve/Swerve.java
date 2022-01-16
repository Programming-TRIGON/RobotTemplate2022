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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotConstants.SwerveConstants;

public class Swerve extends SubsystemBase{
    private final SwerveDriveOdometry swerveOdometry;
    private final SwerveModule[] mSwerveMods;
    private final PigeonIMU gyro;

    public Swerve(){
        gyro = new PigeonIMU(new TalonSRX(SwerveConstants.PIGEON_ID));
        gyro.configFactoryDefault();
        zeroGyro();

        swerveOdometry = new SwerveDriveOdometry(SwerveConstants.SWERVE_KINEMATICS, getYaw());

        mSwerveMods = new SwerveModule[4];
        for(int i = 0; i < 4; i++){
            mSwerveMods[i] = new SwerveModule(SwerveConstants.MODULES[i]);
        }
        SmartDashboard.putNumber("X", swerveOdometry.getPoseMeters().getX());
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop){
        SwerveModuleState[] swerveModuleStates =
                SwerveConstants.SWERVE_KINEMATICS.toSwerveModuleStates(
                        fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                                translation.getX(),
                                translation.getY(),
                                rotation,
                                getYaw()
                        )
                                : new ChassisSpeeds(
                                translation.getX(),
                                translation.getY(),
                                rotation)
                );
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, SwerveConstants.MAX_SPEED);

        if(translation.getNorm() == 0 && rotation == 0){
            for(SwerveModule mod : mSwerveMods){
                swerveModuleStates[mod.moduleNumber].angle = mod.getState().angle;
            }
        }

        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
        }
    }

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates){
        SwerveDriveKinematics.normalizeWheelSpeeds(desiredStates, SwerveConstants.MAX_SPEED);

        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }

    public Pose2d getPose(){
        return swerveOdometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose){
        swerveOdometry.resetPosition(pose, getYaw());
    }

    public SwerveModuleState[] getStates(){
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(SwerveModule mod : mSwerveMods){
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public void zeroGyro(){
        gyro.setYaw(0);
    }

    public Rotation2d getYaw(){
        double[] ypr = new double[3];
        gyro.getYawPitchRoll(ypr);
        return (SwerveConstants.INVERT_GYRO) ? Rotation2d.fromDegrees(360 - ypr[0]) : Rotation2d.fromDegrees(ypr[0]);
    }

    @Override
    public void periodic(){
        swerveOdometry.update(getYaw(), getStates());

        for(SwerveModule mod : mSwerveMods){
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder", mod.getAbsoluteAngle().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getState().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);
        }
        SmartDashboard.putNumber("PigeonIMU", getYaw().getDegrees());
        SmartDashboard.putNumber("X", swerveOdometry.getPoseMeters().getX());
        SmartDashboard.putNumber("Y", swerveOdometry.getPoseMeters().getY());

    }
}