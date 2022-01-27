package frc.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import frc.robot.components.TrigonTalonFX;
import frc.robot.components.TrigonTalonSRX;
import frc.robot.constants.RobotConstants.SwerveConstants;
import frc.robot.constants.RobotConstants.SwerveConstants.SwerveModuleConstants;
import frc.robot.utilities.CTREUtil;
import frc.robot.utilities.EncoderConversions;
import frc.robot.utilities.pid.TrigonPIDController;

/**
 * This class represents a single swerve module.
 * The module has two motors, one for the drive and one for the angle.
 * There's a
 */
public class SwerveModule {
    private final double encoderOffset;
    private final TrigonTalonFX mAngleMotor;
    private final TrigonTalonFX mDriveMotor;
    private final TrigonTalonSRX angleEncoder;
    private final SwerveModuleConstants constants;
    private final TrigonPIDController driveFeedforward;
    private SwerveModuleState lastDesiredState;

    public SwerveModule(SwerveModuleConstants moduleConstants) {
        this.constants = moduleConstants;
        this.driveFeedforward = new TrigonPIDController(
                moduleConstants.drivePIDCoefs);
        encoderOffset = moduleConstants.encoderOffset;

        /* Angle Encoder Config */
        angleEncoder = new TrigonTalonSRX(moduleConstants.encoderID);
        configAngleEncoder();

        /* Angle Motor Config */
        mAngleMotor = new TrigonTalonFX(moduleConstants.angleMotorID);
        configAngleMotor();

        /* Drive Motor Config */
        mDriveMotor = new TrigonTalonFX(moduleConstants.driveMotorID);
        configDriveMotor();
    }

    /**
     * Minimize the change in heading the desired swerve module state would require
     * by potentially
     * reversing the direction the wheel spins. Customized from WPILib's version to
     * include placing
     * in appropriate scope for CTRE onboard control.
     *
     * @param desiredState The desired state.
     * @param currentAngle The current module angle.
     */
    public static SwerveModuleState optimize(
            SwerveModuleState desiredState, Rotation2d currentAngle) {
        double targetAngle = placeInAppropriate0To360Scope(
                currentAngle.getDegrees(), desiredState.angle.getDegrees());
        double targetSpeed = desiredState.speedMetersPerSecond;
        double delta = targetAngle - currentAngle.getDegrees();
        if(Math.abs(delta) > 90) {
            targetSpeed = -targetSpeed;
            targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
        }
        return new SwerveModuleState(
                targetSpeed, Rotation2d.fromDegrees(targetAngle));
    }

    /**
     * @param scopeReference Current Angle
     * @param newAngle       Target Angle
     * @return Closest angle within scope
     */
    private static double placeInAppropriate0To360Scope(
            double scopeReference, double newAngle) {
        double lowerBound;
        double upperBound;
        double lowerOffset = scopeReference % 360;
        if(lowerOffset >= 0) {
            lowerBound = scopeReference - lowerOffset;
            upperBound = scopeReference + (360 - lowerOffset);
        } else {
            upperBound = scopeReference - lowerOffset;
            lowerBound = scopeReference - (360 + lowerOffset);
        }
        while(newAngle < lowerBound) {
            newAngle += 360;
        }
        while(newAngle > upperBound) {
            newAngle -= 360;
        }
        if(newAngle - scopeReference > 180) {
            newAngle -= 360;
        } else if(newAngle - scopeReference < -180) {
            newAngle += 360;
        }
        return newAngle;
    }

    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop) {
        // Custom optimize command, since default WPILib optimize assumes
        // continuous controller which CTRE is not
        desiredState = optimize(
                desiredState,
                getState().angle
        );
        if(isOpenLoop) {
            /* If we're in open loop, we don't want to use the PID controller,
            and we just set the drive speed in percentage of the max speed */
            double percentOutput =
                    desiredState.speedMetersPerSecond / SwerveConstants.MAX_SPEED;
            mDriveMotor.set(
                    ControlMode.PercentOutput,
                    percentOutput / SwerveConstants.SPEED_DIVIDER);
        } else {
            /* If we're in closed loop, we want to use the PID controller,
            and we set the drive speed in meters per second  */
            double velocity = EncoderConversions.MPSToFalcon(
                    desiredState.speedMetersPerSecond,
                    SwerveConstants.WHEEL_CIRCUMFERENCE,
                    SwerveConstants.DRIVE_GEAR_RATIO);
            // Sets the drive motor velocity with the driveFeedforward.
            mDriveMotor.set(
                    ControlMode.Velocity, velocity, DemandType.ArbitraryFeedForward,
                    driveFeedforward.calculate(desiredState.speedMetersPerSecond));
        }

        /*//Prevent rotating module if speed is less than 1%. Prevents jitter.
        double desiredAngle =
                (Math.abs(desiredState.speedMetersPerSecond) <= (SwerveConstants
                .MAX_SPEED * 0.01)) ?
                lastAngle :
                desiredState.angle.getDegrees(); */
        double desiredAngle = desiredState.angle.getDegrees();
        // Set the angle motor's position to the desired angle.
        mAngleMotor.set(
                ControlMode.Position, EncoderConversions.degreesToFalcon(
                        desiredAngle,
                        SwerveConstants.ANGLE_GEAR_RATIO));
        lastDesiredState = desiredState;
    }

    /**
     * Sets the value of the integrated angle encoder to the absolute value of the
     * angle,
     * from the absolute encoder, and the absolute encoder offset.
     */
    private void resetToAbsolute() {
        // calculate absolute position and convert to Falcon units
        double absolutePosition = EncoderConversions.degreesToFalcon(
                getAngle().getDegrees() - encoderOffset,
                SwerveConstants.ANGLE_GEAR_RATIO);
        // Set the integrated angle encoder to the absolute position.
        CTREUtil.checkError(
                () -> mAngleMotor.setSelectedSensorPosition((int) absolutePosition, 0,
                        50), 3);
    }

    /**
     * Configures the angle encoder to the given constants.
     */
    private void configAngleEncoder() {
        CTREUtil.checkError(() -> angleEncoder.configFactoryDefault(30), 3);
        CTREUtil.checkError(
                () -> angleEncoder.configFeedbackNotContinuous(true, 30), 3);
        CTREUtil.checkError(
                () -> angleEncoder.configSelectedFeedbackSensor(
                        TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30),
                3);
        angleEncoder.setSensorPhase(SwerveConstants.ENCODER_INVERT);
    }

    /**
     * Configures the angle motor to the given constants.
     */
    private void configAngleMotor() {
        CTREUtil.checkError(() -> mAngleMotor.configFactoryDefault(30), 3);
        CTREUtil.checkError(
                () -> mAngleMotor.configAllSettings(
                        SwerveConstants.ANGLE_OPEN_LOOP_RAMP,
                        SwerveConstants.ANGLE_CLOSED_LOOP_RAMP,
                        constants.anglePIDCoefs, 30),
                3);
        CTREUtil.checkError(() -> mAngleMotor.configSupplyCurrentLimit(
                SwerveConstants.ANGLE_CURRENT_LIMIT, 30), 3);
        mAngleMotor.setInverted(SwerveConstants.ANGLE_MOTOR_INVERT);
        mAngleMotor.setNeutralMode(SwerveConstants.ANGLE_NEUTRAL_MODE);
        mAngleMotor.setSensorPhase(SwerveConstants.ENCODER_INVERT);
        CTREUtil.checkError(() -> mAngleMotor.configRemoteFeedbackFilter(
                angleEncoder.getDeviceID(),
                RemoteSensorSource.TalonSRX_SelectedSensor, 0, 30), 3);
        CTREUtil.checkError(() -> mAngleMotor.configSelectedFeedbackSensor(
                FeedbackDevice.IntegratedSensor, 0, 30), 3);
        resetToAbsolute();
    }

    /**
     * Configures the drive motor to the given constants.
     */
    private void configDriveMotor() {
        CTREUtil.checkError(() -> mDriveMotor.configFactoryDefault(30), 3);
        CTREUtil.checkError(
                () -> mDriveMotor.configAllSettings(
                        SwerveConstants.DRIVE_OPEN_LOOP_RAMP,
                        SwerveConstants.DRIVE_CLOSED_LOOP_RAMP,
                        constants.drivePIDCoefs, 30),
                3);
        CTREUtil.checkError(() -> mDriveMotor.configSupplyCurrentLimit(
                SwerveConstants.DRIVE_CURRENT_LIMIT, 30), 3);
        mDriveMotor.setInverted(SwerveConstants.DRIVE_MOTOR_INVERT);
        mDriveMotor.setNeutralMode(SwerveConstants.DRIVE_NEUTRAL_MODE);
        CTREUtil.checkError(() -> mDriveMotor.setSelectedSensorPosition(0, 0, 30), 3);
    }

    /**
     * Returns the angle of the module in degrees, using the angle encoder.
     *
     * @return the angle of the module in degrees
     */
    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(
                EncoderConversions.MagToDegrees(angleEncoder.getSelectedSensorPosition()));
    }

    /**
     * Returns the current state of the module.
     * The state is composed of the angle that we get from the angle encoder, and
     * the speed of the drive motor.
     *
     * @return the state of the module
     */
    public SwerveModuleState getState() {
        double velocity = EncoderConversions.falconToMPS(
                mDriveMotor.getSelectedSensorVelocity(),
                SwerveConstants.WHEEL_CIRCUMFERENCE,
                SwerveConstants.DRIVE_GEAR_RATIO);
        Rotation2d angle = Rotation2d.fromDegrees(
                EncoderConversions.falconToDegrees(
                        mAngleMotor.getSelectedSensorPosition(),
                        SwerveConstants.ANGLE_GEAR_RATIO));
        return new SwerveModuleState(velocity, angle);
    }

    /**
     * Returns the desired state of the module.
     *
     * @return the desired state of the module
     */
    public SwerveModuleState getLastDesiredState() {
        return lastDesiredState;
    }

    /**
     * Returns the Talon SRX of the angle encoder.
     *
     * @return the Talon SRX of the angle encoder
     */
    public TalonSRX getEncoderSRX() {
        return angleEncoder;
    }
}