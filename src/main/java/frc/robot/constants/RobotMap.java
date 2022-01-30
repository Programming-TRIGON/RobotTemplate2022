package frc.robot.constants;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public final CAN can = new CAN();
    public final PCM pcm = new PCM();
    public final DIO dio = new DIO();
    public final PWM pwm = new PWM();
    // TODO: Set variables for hardware components

    public class CAN {
        public final SwerveMap swerveMap = new SwerveMap();
        public class SwerveMap {
            public final int PIGEON_ID = 12;

            public final int FRONT_LEFT_DRIVE_MOTOR_ID = 16;
            public final int FRONT_LEFT_ANGLE_MOTOR_ID = 15;
            public final int FRONT_LEFT_ANGLE_ENCODER_ID = 8;

            public final int FRONT_RIGHT_DRIVE_MOTOR_ID = 14;
            public final int FRONT_RIGHT_ANGLE_MOTOR_ID = 13;
            public final int FRONT_RIGHT_ANGLE_ENCODER_ID = 9;

            public final int REAR_LEFT_DRIVE_MOTOR_ID = 6;
            public final int REAR_LEFT_ANGLE_MOTOR_ID = 7;
            public final int REAR_LEFT_ANGLE_ENCODER_ID = 11;

            public final int REAR_RIGHT_DRIVE_MOTOR_ID = 4;
            public final int REAR_RIGHT_ANGLE_MOTOR_ID = 5;
            public final int REAR_RIGHT_ANGLE_ENCODER_ID = 10;
        }
    }

    public class PCM {

    }

    public class DIO {

    }

    public class PWM {
        public final LED led = new LED();
        public class LED {
            public  final int LED_CONTROLLER = -1;
        }
    }
}
