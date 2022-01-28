package frc.robot.utilities.pid;

import edu.wpi.first.wpilibj.controller.PIDController;

public class TrigonPIDFController extends PIDController implements PIDFConfigurable {
    private double v;
    private double s;
    private boolean isTuning;
    private PIDFCoefs pidfCoefs;

    public TrigonPIDFController(PIDFCoefs pidfCoefs) {
        super(pidfCoefs.getKP(), pidfCoefs.getKI(), pidfCoefs.getKD());
        this.pidfCoefs = pidfCoefs;
    }

    /**
     * Returns the next output of the PID controller using Feed-Forward.
     *
     * @param measurement The current measurement of the process variable.
     */
    @Override
    public double calculate(double measurement) {
        return super.calculate(measurement) + v * getSetpoint() + s * Math.signum(getSetpoint());
    }

    /**
     * Get the Velocity gain Feed-Forward coefficient.
     *
     * @return Velocity gain Feed-Forward coefficient
     */
    public double getV() {
        return v;
    }

    /**
     * Sets the Velocity gain coefficient of the Feed-Forward controller gain.
     *
     * @param v Velocity gain Feed-Forward coefficient
     */
    public void setV(double v) {
        this.v = v;
    }

    /**
     * Get the Static gain Feed-Forward coefficient.
     *
     * @return Static gain Feed-Forward coefficient
     */
    public double getS() {
        return s;
    }

    /**
     * Sets the Static gain coefficient of the Feed-Forward controller gain.
     *
     * @param s Static gain Feed-Forward coefficient
     */
    public void setS(double s) {
        this.s = s;
    }

    @Override
    public void updatePID() {
        setP(pidfCoefs.getKP());
        setI(pidfCoefs.getKI());
        setD(pidfCoefs.getKD());
        setV(pidfCoefs.getKV());
        setS(pidfCoefs.getKS());
    }

    @Override
    public PIDFCoefs getCoefs() {
        return pidfCoefs;
    }

    @Override
    public boolean isTuningPID() {
        return isTuning;
    }

    @Override
    public void setIsTuningPID(boolean isTuningPID) {
        this.isTuning = isTuningPID;
    }
}
