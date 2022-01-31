package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import frc.robot.utilities.pid.PIDFCoefs;

/**
 * This class stores the configuration of a motor
 */
public class MotorConfig {

    private double openLoopRampRate;
    private double closedLoopRampRate;
    private boolean isInverted;
    private boolean isSensorInverted;
    private NeutralMode neutralMode;
    private double voltageCompSaturation;
    private SupplyCurrentLimitConfiguration currentLimitConfig;
    private PIDFCoefs coefs;

    /**
     * Empty constructor
     */
    public MotorConfig() {
        openLoopRampRate = 0;
        closedLoopRampRate = 0;
        isInverted = false;
        isSensorInverted = false;
        neutralMode = NeutralMode.Brake;
        voltageCompSaturation = 0;
        currentLimitConfig = new SupplyCurrentLimitConfiguration();
        coefs = new PIDFCoefs();
    }

    /**
     * Copy constructor
     */
    public MotorConfig(MotorConfig config) {
        openLoopRampRate = config.getOpenLoopRampRate();
        closedLoopRampRate = config.getClosedLoopRampRate();
        isInverted = config.isInverted();
        isSensorInverted = config.isSensorInverted();
        neutralMode = config.getNeutralMode();
        voltageCompSaturation = config.getVoltageCompSaturation();
        currentLimitConfig = config.getCurrentLimitConfig();
        coefs = config.getCoefs();
    }

    public double getOpenLoopRampRate() {
        return openLoopRampRate;
    }

    public double getClosedLoopRampRate() {
        return closedLoopRampRate;
    }

    public boolean isInverted() {
        return isInverted;
    }

    public boolean isSensorInverted() {
        return isSensorInverted;
    }

    public NeutralMode getNeutralMode() {
        return neutralMode;
    }

    public double getVoltageCompSaturation() {
        return voltageCompSaturation;
    }

    public SupplyCurrentLimitConfiguration getCurrentLimitConfig() {
        return currentLimitConfig;
    }

    public PIDFCoefs getCoefs() {
        return coefs;
    }

    /**
     * Chain setter for the open loop ramp rate.
     *
     * @param openLoopRampRate Minimum desired time to go from
     *                         neutral to full throttle during open loop. A value of
     *                         '0' will disable the ramp.
     */
    public MotorConfig withOpenLoopRampRate(double openLoopRampRate) {
        this.openLoopRampRate = openLoopRampRate;
        return this;
    }

    /**
     * Chain setter for the PID coefficients.
     *
     * @param coefs The PID coefficients.
     */
    public MotorConfig withPID(PIDFCoefs coefs) {
        this.coefs = coefs;
        return this;
    }

    /**
     * Chain setter for the closed loop ramp rate.
     *
     * @param closedLoopRampRate Minimum desired time to go from
     *                           neutral to full throttle during closed loop. A value of
     *                           '0' will disable the ramp.
     */
    public MotorConfig withClosedLoopRampRate(double closedLoopRampRate) {
        this.closedLoopRampRate = closedLoopRampRate;
        return this;
    }

    /**
     * Chain setter for the neutral mode
     *
     * @param neutralMode The desired mode of operation when the
     *                    Controller output throttle is neutral
     *                    (ie brake/coast)
     */
    public MotorConfig withNeutralMode(NeutralMode neutralMode) {
        this.neutralMode = neutralMode;
        return this;
    }

    /**
     * Chain setter for the voltage compensation saturation
     *
     * @param voltageCompSaturation This is the max voltage to apply to
     *                              the hbridge when voltage compensation
     *                              is enabled. For example, if 10 (volts)
     *                              is specified and a motor controller is
     *                              commanded to 0.5 (PercentOutput,
     *                              closed-loop, etc) then the motor
     *                              controller will attempt to apply a
     *                              duty-cycle to produce 5V. A value of
     *                              '0' disables this feature.
     */
    public MotorConfig withVoltageCompSaturation(double voltageCompSaturation) {
        this.voltageCompSaturation = voltageCompSaturation;
        return this;
    }

    /**
     * Chain setter for the supply current limit configuration
     *
     * @param currentLimitConfig Supply-side current limiting. This is
     *                           typically used to prevent breakers
     *                           from tripping.
     */
    public MotorConfig withCurrentLimitConfig(SupplyCurrentLimitConfiguration currentLimitConfig) {
        this.currentLimitConfig = currentLimitConfig;
        return this;
    }

    /**
     * Chain setter for the sensor inverted
     *
     * @param isSensorInverted Whether the sensor is inverted
     */
    public MotorConfig withSensorInverted(boolean isSensorInverted) {
        this.isSensorInverted = isSensorInverted;
        return this;
    }

    /**
     * Chain setter for the inverted
     *
     * @param isInverted Whether the motor is inverted
     */
    public MotorConfig withInverted(boolean isInverted) {
        this.isInverted = isInverted;
        return this;
    }
}