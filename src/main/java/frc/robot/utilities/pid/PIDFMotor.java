package frc.robot.utilities.pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.utilities.pid.PIDCoefs;
import frc.robot.utilities.pid.PIDConfigurable;

/** An interface used for motors that have a configurable PID */
public interface PIDFMotor extends PIDFConfigurable {
    void setCoefs(PIDFCoefs pidfCoefs);
    void tunePIDF(ControlMode controlMode);
}
