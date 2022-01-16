package frc.robot.constants;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap{
    public static LocalConstants localConstants = JsonHandler.getConstants();
    // TODO: Set variables for hardware components

    public static class CAN{
        public static class SwerveMap{
            public static final int PIGEON_ID = 12;

            public static SwerveModuleConstants[] MODULES = {
                    new SwerveModuleConstants(
                            1,
                            16,
                            15,
                            8,
                            localConstants.localSwerveConstants.modules.frontLeftModuleConstants.encoderOffset,
                            localConstants.localSwerveConstants.modules.frontLeftModuleConstants.angleCoefs,
                            localConstants.localSwerveConstants.modules.frontLeftModuleConstants.driveCoefs

                    ),
                    new SwerveModuleConstants(
                            2,
                            14,
                            13,
                            9,
                            localConstants.localSwerveConstants.modules.frontRightModuleConstants.encoderOffset,
                            localConstants.localSwerveConstants.modules.frontRightModuleConstants.angleCoefs,
                            localConstants.localSwerveConstants.modules.frontRightModuleConstants.driveCoefs
                    ),
                    new SwerveModuleConstants(
                            3,
                            6,
                            7,
                            11,
                            localConstants.localSwerveConstants.modules.rearLeftModuleConstants.encoderOffset,
                            localConstants.localSwerveConstants.modules.rearLeftModuleConstants.angleCoefs,
                            localConstants.localSwerveConstants.modules.rearLeftModuleConstants.driveCoefs
                    ),
                    new SwerveModuleConstants(
                            4,
                            4,
                            5,
                            10,
                            localConstants.localSwerveConstants.modules.rearRightModuleConstants.encoderOffset,
                            localConstants.localSwerveConstants.modules.rearRightModuleConstants.angleCoefs,
                            localConstants.localSwerveConstants.modules.rearRightModuleConstants.driveCoefs
                    )
            };
        }
    }

    public static class PCM{

    }

    public static class DIO{

    }

    public static class PWM{
        public static class LED{
            public static final int LED_CONTROLLER = -1;
        }
    }
}
