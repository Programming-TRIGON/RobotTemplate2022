package frc.robot.utilities.pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.components.TrigonTalonFX;
import frc.robot.utilities.CTREUtil;
import frc.robot.utilities.MotorConfig;

public class PIDFTalonFX extends TrigonTalonFX implements PIDFMotor {
    private final int pidSlotId;
    private PIDFCoefs pidfCoefs;
    private boolean isTuningPID;
    private double tuningPIDSetpoint;

    /**
     * Constructs a new PIDF motor controller
     *
     * @param id          device ID of motor controller
     * @param motorConfig The configuration preset to use
     * @param pidSlotId   the pid slot ID to use
     */
    public PIDFTalonFX(int id, MotorConfig motorConfig, int pidSlotId) {
        super(id, motorConfig);

        this.pidfCoefs = motorConfig.getCoefs();
        this.pidSlotId = pidSlotId;
        this.isTuningPID = false;
        this.tuningPIDSetpoint = 0;

        updatePID();
        CTREUtil.checkError(() -> configAllowableClosedloopError(pidSlotId, (int) pidfCoefs.getTolerance()));
    }

    @Override
    public PIDFCoefs getCoefs() {
        return pidfCoefs;
    }

    @Override
    public void setCoefs(PIDFCoefs pidfCoefs) {
        this.pidfCoefs = pidfCoefs;
    }

    @Override
    public void updatePID() {
        CTREUtil.checkError(() -> config_kP(pidSlotId, pidfCoefs.getKP()));
        CTREUtil.checkError(() -> config_kI(pidSlotId, pidfCoefs.getKI()));
        CTREUtil.checkError(() -> config_kD(pidSlotId, pidfCoefs.getKD()));
        CTREUtil.checkError(() -> config_kF(pidSlotId, pidfCoefs.getKV()));
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
    public boolean isTuningPID() {
        return isTuningPID;
    }

    @Override
    public void setIsTuningPID(boolean isTuningPID) {
        this.isTuningPID = isTuningPID;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        PIDFMotor.super.initSendable(builder);
        builder.setSafeState(this::stopMotor);
        builder.addDoubleProperty("Value", this::get, this::set);
    }
}
