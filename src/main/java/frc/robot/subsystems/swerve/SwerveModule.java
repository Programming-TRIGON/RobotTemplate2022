package frc.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.RobotConstants.SwerveConstants;
import frc.robot.constants.SwerveModuleConstants;
import frc.robot.constants.TrigonTalonFXConfig;
import frc.robot.utilities.CTREUtil;
import frc.robot.utilities.Conversions;
import frc.robot.utilities.TrigonPIDController;

public class SwerveModule{
    public int moduleNumber;
    private final double encoderOffset;
    private final TalonFX mAngleMotor;
    private final TalonFX mDriveMotor;
    private final TalonSRX angleEncoder;
    private final SwerveModuleConstants constants;
    private double lastAngle;

    TrigonPIDController driveFeedforward;

    public SwerveModule(SwerveModuleConstants moduleConstants){
        this.constants = moduleConstants;
        this.moduleNumber = moduleConstants.moduleNumber;
        this.driveFeedforward = new TrigonPIDController(moduleConstants.drivePIDCoefs);
        encoderOffset = moduleConstants.encoderOffset;

        /* Angle Encoder Config */
        angleEncoder = new TalonSRX(moduleConstants.encoderID);
        configAngleEncoder();

        /* Angle Motor Config */
        mAngleMotor = new TalonFX(moduleConstants.angleMotorID);
        configAngleMotor();

        /* Drive Motor Config */
        mDriveMotor = new TalonFX(moduleConstants.driveMotorID);
        configDriveMotor();

        lastAngle = getState().angle.getDegrees();
    }

    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop){
        desiredState = optimize(desiredState, getState().angle); //Custom optimize command, since default WPILib optimize assumes continuous controller which CTRE is not
        if(isOpenLoop){
            double percentOutput = desiredState.speedMetersPerSecond / SwerveConstants.MAX_SPEED;
            mDriveMotor.set(ControlMode.PercentOutput, percentOutput / SwerveConstants.SPEED_DIVIDER);
        } else{
            double velocity = Conversions.MPSToFalcon(desiredState.speedMetersPerSecond, SwerveConstants.WHEEL_CIRCUMFERENCE, SwerveConstants.DRIVE_GEAR_RATIO);
            mDriveMotor.set(ControlMode.Velocity, velocity / 15, DemandType.ArbitraryFeedForward, driveFeedforward.calculate(desiredState.speedMetersPerSecond));
        }

        //double desiredAngle = (Math.abs(desiredState.speedMetersPerSecond) <= (Constants.Swerve.maxSpeed * 0.01)) ? lastAngle : desiredState.angle.getDegrees(); //Prevent rotating module if speed is less then 1%. Prevents Jittering.
        double desiredAngle = desiredState.angle.getDegrees();
        mAngleMotor.set(ControlMode.Position, Conversions.degreesToFalcon(desiredAngle, SwerveConstants.ANGLE_GEAR_RATIO));
        if(moduleNumber == 2)
            SmartDashboard.putNumber("Mod 2 angle", desiredAngle);
        lastAngle = desiredAngle;
        SmartDashboard.putNumber("Mod " + this.moduleNumber + " Desired", desiredAngle);

    }

    private void resetToAbsolute(){
        double absolutePosition = Conversions.degreesToFalcon(getAbsoluteAngle().getDegrees() - encoderOffset, SwerveConstants.ANGLE_GEAR_RATIO);
        CTREUtil.checkError(() -> mAngleMotor.setSelectedSensorPosition((int) absolutePosition, 0, 50), 3);
        if(moduleNumber == 2){
            System.out.println("_____" + getAbsoluteAngle().getDegrees());
            System.out.println("_____" + encoderOffset);
            System.out.println("_____" + absolutePosition);
        }
    }

    private void configAngleEncoder(){
        CTREUtil.checkError(() -> angleEncoder.configFactoryDefault(30), 3);
        CTREUtil.checkError(() -> angleEncoder.configFeedbackNotContinuous(true, 30), 3);
        CTREUtil.checkError(() -> angleEncoder.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30), 3);
        angleEncoder.setSensorPhase(SwerveConstants.ENCODER_INVERT);
    }

    private void configAngleMotor(){
        CTREUtil.checkError(() -> mAngleMotor.configFactoryDefault(30), 3);
        TrigonTalonFXConfig config = new TrigonTalonFXConfig(
                SwerveConstants.ANGLE_OPEN_LOOP_RAMP,
                SwerveConstants.ANGLE_CLOSED_LOOP_RAMP,
                constants.anglePIDCoefs
        );
        CTREUtil.checkError(() -> mAngleMotor.configAllSettings(config, 30), 3);
        CTREUtil.checkError(() -> mAngleMotor.configSupplyCurrentLimit(SwerveConstants.ANGLE_CURRENT_LIMIT, 30), 3);
        mAngleMotor.setInverted(SwerveConstants.ANGLE_MOTOR_INVERT);
        mAngleMotor.setNeutralMode(SwerveConstants.ANGLE_NEUTRAL_MODE);
        mAngleMotor.setSensorPhase(SwerveConstants.ENCODER_INVERT);
        CTREUtil.checkError(() -> mAngleMotor.configRemoteFeedbackFilter(angleEncoder.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, 30), 3);
        CTREUtil.checkError(() -> mAngleMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30), 3);
        resetToAbsolute();
    }

    private void configDriveMotor(){
        CTREUtil.checkError(() -> mDriveMotor.configFactoryDefault(30), 3);
        TrigonTalonFXConfig config = new TrigonTalonFXConfig(
                SwerveConstants.DRIVE_OPEN_LOOP_RAMP,
                SwerveConstants.DRIVE_CLOSED_LOOP_RAMP,
                constants.drivePIDCoefs
        );
        CTREUtil.checkError(() -> mDriveMotor.configAllSettings(config, 30), 3);
        CTREUtil.checkError(() -> mDriveMotor.configSupplyCurrentLimit(SwerveConstants.DRIVE_CURRENT_LIMIT, 30), 3);
        mDriveMotor.setInverted(SwerveConstants.DRIVE_MOTOR_INVERT);
        mDriveMotor.setNeutralMode(SwerveConstants.DRIVE_NEUTRAL_MODE);
        CTREUtil.checkError(() -> mDriveMotor.setSelectedSensorPosition(0, 0, 30), 3);
    }

    public Rotation2d getAbsoluteAngle(){
        return Rotation2d.fromDegrees(Conversions.MagToDegrees(angleEncoder.getSelectedSensorPosition()));
    }

    public SwerveModuleState getState(){
        double velocity = Conversions.falconToMPS(mDriveMotor.getSelectedSensorVelocity(), SwerveConstants.WHEEL_CIRCUMFERENCE, SwerveConstants.DRIVE_GEAR_RATIO);
        Rotation2d angle = Rotation2d.fromDegrees(Conversions.falconToDegrees(mAngleMotor.getSelectedSensorPosition(), SwerveConstants.ANGLE_GEAR_RATIO));
        return new SwerveModuleState(velocity, angle);
    }

    /**
     * Minimize the change in heading the desired swerve module state would require by potentially
     * reversing the direction the wheel spins. Customized from WPILib's version to include placing
     * in appropriate scope for CTRE onboard control.
     *
     * @param desiredState The desired state.
     * @param currentAngle The current module angle.
     */
    public static SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle){
        double targetAngle = placeInAppropriate0To360Scope(currentAngle.getDegrees(), desiredState.angle.getDegrees());
        double targetSpeed = desiredState.speedMetersPerSecond;
        double delta = targetAngle - currentAngle.getDegrees();
        if(Math.abs(delta) > 90){
            targetSpeed = -targetSpeed;
            targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
        }
        return new SwerveModuleState(targetSpeed, Rotation2d.fromDegrees(targetAngle));
    }

    /**
     * @param scopeReference Current Angle
     * @param newAngle       Target Angle
     * @return Closest angle within scope
     */
    private static double placeInAppropriate0To360Scope(double scopeReference, double newAngle){
        double lowerBound;
        double upperBound;
        double lowerOffset = scopeReference % 360;
        if(lowerOffset >= 0){
            lowerBound = scopeReference - lowerOffset;
            upperBound = scopeReference + (360 - lowerOffset);
        } else{
            upperBound = scopeReference - lowerOffset;
            lowerBound = scopeReference - (360 + lowerOffset);
        }
        while(newAngle < lowerBound){
            newAngle += 360;
        }
        while(newAngle > upperBound){
            newAngle -= 360;
        }
        if(newAngle - scopeReference > 180){
            newAngle -= 360;
        } else if(newAngle - scopeReference < -180){
            newAngle += 360;
        }
        return newAngle;
    }
}