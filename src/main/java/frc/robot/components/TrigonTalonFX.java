package frc.robot.components;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.utilities.MotorConfig;
import frc.robot.utilities.pid.PIDCoefs;

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
    public TrigonTalonFX(int id) {
        this(id, new MotorConfig());
    }

    public ErrorCode configAllSettings(double openLoopRamp, double closedLoopRamp, PIDCoefs pidCoefs, int timeoutMs) {
        TalonFXConfiguration config = new TalonFXConfiguration();
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