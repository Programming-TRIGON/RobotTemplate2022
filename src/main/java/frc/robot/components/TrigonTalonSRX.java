package frc.robot.components;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.utilities.MotorConfig;
import frc.robot.utilities.PIDCoefs;

/**
 * This class creates a new instance of WPI_TalonSRX and configures values based
 * on a config preset
 */
public class TrigonTalonSRX extends WPI_TalonSRX {

    /**
     * constructs a new motor controller
     *
     * @param id          device ID of motor controller
     * @param motorConfig The configuration preset to use
     */
    public TrigonTalonSRX(int id, MotorConfig motorConfig) {
        super(id);
        configOpenloopRamp(motorConfig.getRampRate());
        configClosedloopRamp(motorConfig.getRampRate());
        setInverted(motorConfig.isInverted());
        setSensorPhase(motorConfig.isInverted());
        setNeutralMode(motorConfig.getNeutralMode());
        configVoltageCompSaturation(motorConfig.getVoltageCompSaturation());
        enableVoltageCompensation(motorConfig.getVoltageCompSaturation() > 0);
        configSupplyCurrentLimit(motorConfig.getSupplyCurrentLimitConfiguration());
    }

    /**
     * constructs a new motor controller with a default configuration
     *
     * @param id device ID of motor controller
     */
    public TrigonTalonSRX(int id) {
        this(id, new MotorConfig());
    }

    public ErrorCode configAllSettings(double openLoopRamp, double closedLoopRamp, PIDCoefs pidCoefs, int timeoutMs) {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.openloopRamp = openLoopRamp;
        config.closedloopRamp = closedLoopRamp;
        config.slot0.kP = pidCoefs.getKP();
        config.slot0.kI = pidCoefs.getKI();
        config.slot0.kD = pidCoefs.getKD();
        config.slot0.kF = pidCoefs.getKF();
        config.slot0.allowableClosedloopError = (int) pidCoefs.getTolerance();

        return this.configAllSettings(config);
    }

    public ErrorCode configAllSettings(double openLoopRamp, double closedLoopRamp, PIDCoefs pidCoefs) {
        return configAllSettings(openLoopRamp, closedLoopRamp, pidCoefs, 0);
    }
}