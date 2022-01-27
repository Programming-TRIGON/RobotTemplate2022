package frc.robot.utilities.pid;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public interface PIDConfigurable extends Sendable {

    void updatePID();

    PIDCoefs getCoefs();

    double getSetpoint();

    void setSetpoint(double setpoint);

    boolean isTuningPID();

    void setIsTuningPID(boolean isTuningPID);

    default double getKP() {
        return getCoefs().getKP();
    }

    default void setKP(double KP) {
        getCoefs().setKP(KP);
        updatePID();
    }

    default double getKI() {
        return getCoefs().getKI();
    }

    default void setKI(double KI) {
        getCoefs().setKI(KI);
        updatePID();
    }

    default double getKD() {
        return getCoefs().getKD();
    }

    default void setKD(double KD) {
        getCoefs().setKD(KD);
        updatePID();
    }

    @Override
    default void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("RobotPreferences");
        // sends the pid values to the dashboard but only allows them to be changed if
        // isTuning is true
        builder.addDoubleProperty("p", this::getKP, kP -> setKP(isTuningPID() ? kP : getKP()));
        builder.addDoubleProperty("i", this::getKI, kI -> setKI(isTuningPID() ? kI : getKI()));
        builder.addDoubleProperty("d", this::getKD, kD -> setKD(isTuningPID() ? kD : getKD()));
        builder.addDoubleProperty("setpoint", this::getSetpoint,
                setpoint -> setSetpoint(isTuningPID() ? setpoint : getSetpoint()));
        builder.addBooleanProperty("isTuning", this::isTuningPID, this::setIsTuningPID);
    }
}
