package frc.robot.constants;

import com.google.gson.annotations.SerializedName;
import frc.robot.utilities.JsonHandler;
import frc.robot.utilities.PIDCoefs;

public class LocalConstants {
    @SerializedName("Swerve")
    LocalSwerveConstants localSwerveConstants;

    public LocalConstants() {
        localSwerveConstants = new LocalSwerveConstants();
    }

    public void write() {
        JsonHandler.write(this);
    }

    public static class LocalSwerveConstants {
        String angleNeutralMode, driveNeutralMode;
        LocalSwerveModules modules;

        public LocalSwerveConstants() {
            angleNeutralMode = driveNeutralMode = "Coast";
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

                PIDCoefs angleCoefs;
                PIDCoefs driveCoefs;

                public LocalSwerveModuleConstants() {
                    encoderOffset = 0;
                    angleCoefs = new PIDCoefs();
                    driveCoefs = new PIDCoefs();
                }
            }
        }
    }
}
