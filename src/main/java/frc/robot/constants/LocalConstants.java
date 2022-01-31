package frc.robot.constants;

import com.google.gson.annotations.SerializedName;
import frc.robot.utilities.JsonHandler;
import frc.robot.utilities.pid.PIDFCoefs;

public class LocalConstants {
    @SerializedName("Swerve")
    public LocalSwerveConstants localSwerveConstants;

    @SerializedName("Driver")
    public LocalDriverConstants localDriverConstants;

    public LocalConstants() {
        localSwerveConstants = new LocalSwerveConstants();
        localDriverConstants = new LocalDriverConstants();
    }

    public void write() {
        JsonHandler.write(this);
    }

    public static class LocalSwerveConstants {
        String angleNeutralMode;
        String driveNeutralMode;
        LocalSwerveModules modules;

        public LocalSwerveConstants() {
            angleNeutralMode = "Coast";
            driveNeutralMode = "Coast";
            modules = new LocalSwerveModules();
            modules.frontRightModuleConstants = new LocalSwerveModules.LocalSwerveModuleConstants();
            modules.frontLeftModuleConstants = new LocalSwerveModules.LocalSwerveModuleConstants();
            modules.rearRightModuleConstants = new LocalSwerveModules.LocalSwerveModuleConstants();
            modules.rearLeftModuleConstants = new LocalSwerveModules.LocalSwerveModuleConstants();
        }

        public static class LocalSwerveModules {
            @SerializedName("frontRight")
            public LocalSwerveModuleConstants frontRightModuleConstants;
            @SerializedName("frontLeft")
            public LocalSwerveModuleConstants frontLeftModuleConstants;
            @SerializedName("rearRight")
            public LocalSwerveModuleConstants rearRightModuleConstants;
            @SerializedName("rearLeft")
            public LocalSwerveModuleConstants rearLeftModuleConstants;

            public static class LocalSwerveModuleConstants {
                double encoderOffset;

                PIDFCoefs angleCoefs;
                PIDFCoefs driveCoefs;

                public LocalSwerveModuleConstants() {
                    encoderOffset = 0;
                    angleCoefs = new PIDFCoefs();
                    driveCoefs = new PIDFCoefs();
                }
            }
        }
    }

    public static class LocalDriverConstants {
        int speedDivider;
    }
}
