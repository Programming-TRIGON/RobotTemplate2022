package frc.robot.utilities.pid;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.utilities.pid.PIDCoefs;
import frc.robot.utilities.pid.PIDConfigurable;

public class TrigonPIDController extends PIDController implements PIDConfigurable {
    private double f;
    private boolean isTuning;
    private PIDCoefs pidCoefs;

    public TrigonPIDController(PIDCoefs pidCoefs) {
        super(pidCoefs.getKP(), pidCoefs.getKI(), pidCoefs.getKD());
        this.pidCoefs = pidCoefs;
        setTolerance(pidCoefs.getTolerance(), pidCoefs.getDeltaTolerance());
        f = pidCoefs.getKF();
        isTuning = false;
    }

    /**
     * Get the Feed-Forward coefficient.
     *
     * @return Feed-Forward coefficient
     */
    public double getF() {
        return f;
    }

    /**
     * Sets the Feed-Forward coefficient of the PID controller gain.
     *
     * @param f Feed-Forward coefficient
     */
    public void setF(double f) {
        this.f = f;
    }

    /**
     * Returns the next output of the PID controller using Feed-Forward.
     *
     * @param measurement The current measurement of the process variable.
     */
    public double calculateWithKF(double measurement) {
        return calculate(measurement) + f * getSetpoint();
    }


    @Override
    public PIDCoefs getCoefs() {
        return pidCoefs;
    }

    @Override
    public void updatePID() {
        setP(pidCoefs.getKP());
        setI(pidCoefs.getKI());
        setD(pidCoefs.getKD());
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
