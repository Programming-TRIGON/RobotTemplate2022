package frc.robot.components;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.constants.RobotConstants;
import frc.robot.utilities.CTREUtil;
import frc.robot.utilities.MotorConfig;

/**
 * This class creates a new instance of WPI_TalonFX and configures values based
 * on a config preset
 */
public class TrigonTalonFX extends WPI_TalonFX {

    /**
     * constructs a new motor controller
     *
     * @param id          device ID of motor controller
     * @param motorConfig The configuration preset to use
     */
    public TrigonTalonFX(int id, MotorConfig motorConfig) {
        super(id);

        configFactoryDefault();
        setInverted(motorConfig.isInverted());
        setSensorPhase(motorConfig.isSensorInverted());
        setNeutralMode(motorConfig.getNeutralMode());
        enableVoltageCompensation(motorConfig.getVoltageCompSaturation() > 0);
        configFeedbackNotContinuous(motorConfig.isFeedbackNotContinuous());
        configOpenloopRamp(motorConfig.getOpenLoopRampRate());
        configClosedloopRamp(motorConfig.getClosedLoopRampRate());
        configVoltageCompSaturation(motorConfig.getVoltageCompSaturation());
        configSupplyCurrentLimit(motorConfig.getCurrentLimitConfig());
        configSelectedFeedbackSensor(motorConfig.getFeedbackDevice());
        configRemoteFeedbackFilter(
                motorConfig.getRemoteSensorSourceDeviceId(), motorConfig.getRemoteSensorSourceType());
        config_kP(0, motorConfig.getCoefs().getKP());
        config_kI(0, motorConfig.getCoefs().getKI());
        config_kD(0, motorConfig.getCoefs().getKD());
        config_kF(0, motorConfig.getCoefs().getKV());
        configAllowableClosedloopError(0, (int) motorConfig.getCoefs().getTolerance());
    }

    /**
     * constructs a new motor controller with a default configuration
     *
     * @param id device ID of motor controller
     */
    public TrigonTalonFX(int id) {
        this(id, new MotorConfig());
    }

    @Override
    public ErrorCode configFactoryDefault() {
        return CTREUtil.checkError(() -> configFactoryDefault(RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode configOpenloopRamp(double seconds) {
        return CTREUtil.checkError(() -> configOpenloopRamp(seconds, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode configClosedloopRamp(double seconds) {
        return CTREUtil.checkError(() -> configClosedloopRamp(seconds, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode configVoltageCompSaturation(double volts) {
        return CTREUtil.checkError(() -> configVoltageCompSaturation(volts, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode configSupplyCurrentLimit(SupplyCurrentLimitConfiguration config) {
        return CTREUtil.checkError(() -> configSupplyCurrentLimit(config, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode config_kP(int slotIdx, double value) {
        return CTREUtil.checkError(() -> config_kP(slotIdx, value, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode config_kI(int slotIdx, double value) {
        return CTREUtil.checkError(() -> config_kI(slotIdx, value, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode config_kD(int slotIdx, double value) {
        return CTREUtil.checkError(() -> config_kD(slotIdx, value, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode config_kF(int slotIdx, double value) {
        return CTREUtil.checkError(() -> config_kF(slotIdx, value, RobotConstants.DEF_CAN_TIMEOUT));
    }

    public ErrorCode configAllowableClosedloopError(int slotIdx, int allowableCloseLoopError) {
        return CTREUtil.checkError(
                () -> configAllowableClosedloopError(slotIdx, allowableCloseLoopError, RobotConstants.DEF_CAN_TIMEOUT));
    }

    @Override
    public ErrorCode configRemoteFeedbackFilter(
            int deviceID, RemoteSensorSource remoteSensorSource, int remoteOrdinal) {
        return CTREUtil.checkError(() -> configRemoteFeedbackFilter(deviceID, remoteSensorSource, remoteOrdinal,
                RobotConstants.DEF_CAN_TIMEOUT));
    }

    public ErrorCode configSelectedFeedbackSensor(FeedbackDevice feedbackDevice, int pidIdx) {
        return CTREUtil.checkError(
                () -> configSelectedFeedbackSensor(feedbackDevice, pidIdx, RobotConstants.DEF_CAN_TIMEOUT));
    }

    public ErrorCode configSelectedFeedbackSensor(FeedbackDevice feedbackDevice) {
        return configSelectedFeedbackSensor(feedbackDevice, 0);
    }

    public ErrorCode setSelectedSensorPosition(int sensorPos, int pidIdx) {
        return CTREUtil.checkError(() -> setSelectedSensorPosition(sensorPos, pidIdx, RobotConstants.DEF_CAN_TIMEOUT));
    }

    public ErrorCode setSelectedSensorPosition(int sensorPos) {
        return setSelectedSensorPosition(sensorPos, 0);
    }

    public ErrorCode configFeedbackNotContinuous(boolean feedbackNotContinuous) {
        return CTREUtil.checkError(
                () -> configFeedbackNotContinuous(feedbackNotContinuous, RobotConstants.DEF_CAN_TIMEOUT));
    }

    public ErrorCode configRemoteFeedbackFilter(int deviceID, RemoteSensorSource remoteSensorSource) {
        return configRemoteFeedbackFilter(deviceID, remoteSensorSource, 0);
    }
}
