package frc.robot.utilities.pid;

import edu.wpi.first.wpilibj.controller.PIDController;

public class TrigonPIDController extends PIDController implements PIDConfigurable {
    private boolean isTuning;
    private PIDCoefs pidCoefs;

    public TrigonPIDController(PIDCoefs pidCoefs) {
        super(pidCoefs.getKP(), pidCoefs.getKI(), pidCoefs.getKD());
        this.pidCoefs = pidCoefs;
        setTolerance(pidCoefs.getTolerance(), pidCoefs.getDeltaTolerance());
        isTuning = false;
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
