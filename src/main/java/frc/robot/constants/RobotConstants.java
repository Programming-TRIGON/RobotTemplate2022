package frc.robot.constants;

import frc.robot.utilities.PIDCoefs;

/**
 * All the constants to be uses for the robot
 */
public class RobotConstants extends RobotMap {
    public LocalConstants localConstants = JsonHandler.getConstants();

    public static class LimelightConstants {
        public final double DISTANCE_CALCULATION_A_COEFFICIENT = 1;
        public final double DISTANCE_CALCULATION_B_COEFFICIENT = 1;
        public final double DISTANCE_CALCULATION_C_COEFFICIENT = 1;
        public final double LIMELIGHT_ANGLE_OFFSET = 1;
        public final double LIMELIGHT_OFFSET_X = 1;
        public final double LIMELIGHT_OFFSET_Y = 1;
        public final String DEFAULT_TABLE_KEY = "limelight";
    }

    public static class TesterConstants {
        public final int SECONDS_TO_WAIT = 1;
        public final double MOVE_POWER = 3;
        public final int LED_BLINK_AMOUNT = 10;
    }

    public static class VisionConstants {
        public final PIDCoefs ROTATION_SETTINGS = new PIDCoefs(0, 0, 0, 0, 0);
        public final double TARGET_TIME_OUT = 0.1;

    }

}
