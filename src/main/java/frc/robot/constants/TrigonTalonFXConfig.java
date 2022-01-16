package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import frc.robot.utilities.PIDCoefs;

public class TrigonTalonFXConfig extends TalonFXConfiguration{
    public TrigonTalonFXConfig(double openLoopRamp,
                               double closedLoopRamp,
                               PIDCoefs pidCoefs
    ){
        this.openloopRamp = openLoopRamp;
        this.closedloopRamp = closedLoopRamp;
        slot0.kP = pidCoefs.getKP();
        slot0.kI = pidCoefs.getKI();
        slot0.kD = pidCoefs.getKD();
        slot0.kF = pidCoefs.getKF();
        slot0.allowableClosedloopError = (int) pidCoefs.getTolerance();
    }
}
