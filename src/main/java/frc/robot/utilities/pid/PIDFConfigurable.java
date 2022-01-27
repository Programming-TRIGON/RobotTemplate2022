package frc.robot.utilities.pid;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public interface PIDFConfigurable extends PIDConfigurable {

    @Override
    PIDFCoefs getCoefs();

    default double getKV() {
        return getCoefs().getKV();
    }

    default void setKV(double KV) {
        getCoefs().setKV(KV);
        updatePID();
    }

    default double getKS() {
        return getCoefs().getKS();
    }

    default void setKS(double KS) {
        getCoefs().setKS(KS);
        updatePID();
    }

    @Override
    default void initSendable(SendableBuilder builder) {
        PIDConfigurable.super.initSendable(builder);
        builder.addDoubleProperty("v", this::getKS, kS -> setKS(isTuningPID() ? kS : getKS()));
        builder.addDoubleProperty("s", this::getKV, kV -> setKV(isTuningPID() ? kV : getKV()));
    }
}
