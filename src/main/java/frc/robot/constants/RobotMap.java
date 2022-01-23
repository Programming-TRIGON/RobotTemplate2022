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
    // TODO: Set variables for hardware components

    public static class CAN {
        public static class SwerveMap {
            public static final int PIGEON_ID = 12;

            public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 16;
            public static final int FRONT_LEFT_ANGLE_MOTOR_ID = 15;
            public static final int FRONT_LEFT_ANGLE_ENCODER_ID = 8;

            public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 14;
            public static final int FRONT_RIGHT_ANGLE_MOTOR_ID = 13;
            public static final int FRONT_RIGHT_ANGLE_ENCODER_ID = 9;

            public static final int REAR_LEFT_DRIVE_MOTOR_ID = 6;
            public static final int REAR_LEFT_ANGLE_MOTOR_ID = 7;
            public static final int REAR_LEFT_ANGLE_ENCODER_ID = 11;

            public static final int REAR_RIGHT_DRIVE_MOTOR_ID = 4;
            public static final int REAR_RIGHT_ANGLE_MOTOR_ID = 5;
            public static final int REAR_RIGHT_ANGLE_ENCODER_ID = 10;
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
