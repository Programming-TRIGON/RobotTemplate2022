package frc.robot.constants;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final LocalConstants LOCAL_CONSTANTS = JsonHandler.getConstants();
    // TODO: Set variables for hardware components

    public static class CAN {
        public static class SwerveMap {
            public static final int PIGEON_ID = 12;

            public static SwerveModuleConstants[] MODULES = {
                    new SwerveModuleConstants(
                            1,
                            16,
                            15,
                            8,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.encoderOffset,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.angleCoefs,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.driveCoefs

                    ),
                    new SwerveModuleConstants(
                            2,
                            14,
                            13,
                            9,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.encoderOffset,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.angleCoefs,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.driveCoefs
                    ),
                    new SwerveModuleConstants(
                            3,
                            6,
                            7,
                            11,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.encoderOffset,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.angleCoefs,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.driveCoefs
                    ),
                    new SwerveModuleConstants(
                            4,
                            4,
                            5,
                            10,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.encoderOffset,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.angleCoefs,
                            LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.driveCoefs
                    )
            };
        }
    }

    public static class PCM {

    }

    public static class DIO {

    }

    public static class PWM {
        public static class LED {
            public static final int LED_CONTROLLER = -1;
        }
    }
}
