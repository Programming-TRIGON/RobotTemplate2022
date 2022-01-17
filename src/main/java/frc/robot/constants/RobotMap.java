package frc.robot.constants;

import frc.robot.utilities.JsonHandler;
import frc.robot.utilities.PIDCoefs;

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

            public static final SwerveModuleConstants FRONT_LEFT_CONSTANTS = new SwerveModuleConstants(
                    16,
                    15,
                    8,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.encoderOffset,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.angleCoefs,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.driveCoefs
            );
            public static final SwerveModuleConstants FRONT_RIGHT_CONSTANTS = new SwerveModuleConstants(
                    14,
                    13,
                    9,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.encoderOffset,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.angleCoefs,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.driveCoefs
            );
            public static final SwerveModuleConstants REAR_LEFT_CONSTANTS = new SwerveModuleConstants(
                    6,
                    7,
                    11,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.encoderOffset,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.angleCoefs,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.driveCoefs
            );
            public static final SwerveModuleConstants REAR_RIGHT_CONSTANTS = new SwerveModuleConstants(
                    4,
                    5,
                    10,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.encoderOffset,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.angleCoefs,
                    LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.driveCoefs
            );

            public static class SwerveModuleConstants {
                public final int driveMotorID;
                public final int angleMotorID;
                public final int encoderID;
                public final double encoderOffset;
                public final PIDCoefs anglePIDCoefs, drivePIDCoefs;

                public SwerveModuleConstants(
                        int driveMotorID, int angleMotorID, int encoderID, double encoderOffset,
                        PIDCoefs anglePIDCoefs, PIDCoefs drivePIDCoefs) {
                    this.driveMotorID = driveMotorID;
                    this.angleMotorID = angleMotorID;
                    this.encoderID = encoderID;
                    this.encoderOffset = encoderOffset;
                    this.anglePIDCoefs = anglePIDCoefs;
                    this.drivePIDCoefs = drivePIDCoefs;
                }
            }
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
