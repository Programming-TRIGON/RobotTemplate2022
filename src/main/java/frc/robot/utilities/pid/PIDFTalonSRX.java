package frc.robot.utilities.pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.components.TrigonTalonSRX;
import frc.robot.utilities.MotorConfig;

public class PIDFTalonSRX extends TrigonTalonSRX implements PIDFMotor {
    private PIDFCoefs pidfCoefs;
    private boolean isTuningPID;
    private double tuningPIDSetpoint;

    /**
     * Constructs a new PIDF motor controller
     *
     * @param id          device ID of motor controller
     * @param motorConfig The configuration preset to use
     */
    public PIDFTalonSRX(int id, MotorConfig motorConfig) {
        super(id, motorConfig);

        setCoefs(pidfCoefs);
        this.isTuningPID = false;
        this.tuningPIDSetpoint = 0;
    }

    @Override
    public PIDFCoefs getCoefs() {
        return pidfCoefs;
    }

    @Override
    public void setCoefs(PIDFCoefs pidfCoefs) {
        this.pidfCoefs = pidfCoefs;
        setKP(pidfCoefs.getKP());
        setKI(pidfCoefs.getKI());
        setKD(pidfCoefs.getKD());
        setKV(pidfCoefs.getKV());
        setKS(pidfCoefs.getKS());
        setTolerance(pidfCoefs.getTolerance());
    }

    public void updatePID() {
        config_kP(0, pidfCoefs.getKP());
        config_kI(0, pidfCoefs.getKI());
        config_kD(0, pidfCoefs.getKD());
        config_kF(0, pidfCoefs.getKV());
    }

    @Override
    public void tunePIDF(ControlMode controlMode) {
        set(controlMode, tuningPIDSetpoint, DemandType.ArbitraryFeedForward, pidfCoefs.getKS());
    }

    public void setWithF(ControlMode controlMode, double setpoint) {
        set(controlMode, setpoint, DemandType.ArbitraryFeedForward, pidfCoefs.getKS());
    }

    @Override
    public double getSetpoint() {
        return isTuningPID ? tuningPIDSetpoint : getClosedLoopTarget();
    }

    /**
     * sets the setpoint for the PID during tuning.
     */
    @Override
    public void setSetpoint(double setpoint) {
        tuningPIDSetpoint = setpoint;
    }

    @Override
    public boolean isTuning() {
        return isTuningPID;
    }

    @Override
    public void setIsTuning(boolean isTuningPID) {
        this.isTuningPID = isTuningPID;
    }

    @Override
    public void setKP(double p) {
        pidfCoefs.setKP(p);
        config_kP(0, p);
    }

    @Override
    public void setKI(double i) {
        pidfCoefs.setKI(i);
        config_kI(0, i);
    }

    @Override
    public void setKD(double d) {
        pidfCoefs.setKD(d);
        config_kD(0, d);
    }

    @Override
    public void setKS(double s) {
        pidfCoefs.setKS(s);
    }

    @Override
    public void setKV(double v) {
        pidfCoefs.setKV(v);
        config_kF(0, v);
    }

    @Override
    public void setTolerance(double tolerance) {
        configAllowableClosedloopError(0, (int) tolerance);
    }

    @Override
    public void setDeltaTolerance(double deltaTolerance) {
        //  This does nothing
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        PIDFMotor.super.initSendable(builder);
        builder.setSafeState(this::stopMotor);
        builder.addDoubleProperty("Value", this::get, this::set);
    }
}
