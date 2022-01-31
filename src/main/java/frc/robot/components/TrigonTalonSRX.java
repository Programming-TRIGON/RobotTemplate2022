package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.utilities.MotorConfig;

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
        configOpenloopRamp(motorConfig.getOpenLoopRampRate());
        configClosedloopRamp(motorConfig.getOpenLoopRampRate());
        setInverted(motorConfig.isInverted());
        setSensorPhase(motorConfig.isInverted());
        setNeutralMode(motorConfig.getNeutralMode());
        configVoltageCompSaturation(motorConfig.getVoltageCompSaturation());
        enableVoltageCompensation(motorConfig.getVoltageCompSaturation() > 0);
        configSupplyCurrentLimit(motorConfig.getCurrentLimitConfig());
        config_kP(0, motorConfig.getCoefs().getKP());
        config_kI(0, motorConfig.getCoefs().getKI());
        config_kD(0, motorConfig.getCoefs().getKD());
        config_kF(0, motorConfig.getCoefs().getKV());
    }

    /**
     * constructs a new motor controller with a default configuration
     *
     * @param id device ID of motor controller
     */
    public TrigonTalonSRX(int id) {
        this(id, new MotorConfig());
    }
}