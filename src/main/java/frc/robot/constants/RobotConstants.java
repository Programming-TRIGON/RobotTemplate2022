package frc.robot.constants;

import frc.robot.utilities.PIDCoefs;

/**
 * All the constants to be uses for the robot
 */
public class RobotConstants extends RobotMap{
    public LocalConstants localConstants = JsonHandler.getConstants();

    public static class LimelightConstants{
        public double DISTANCE_CALCULATION_A_COEFFICIENT = 1;
        public double DISTANCE_CALCULATION_B_COEFFICIENT = 1;
        public double DISTANCE_CALCULATION_C_COEFFICIENT = 1;
        public double LIMELIGHT_ANGLE_OFFSET = 1;
        public double LIMELIGHT_OFFSET_X = 1;
        public double LIMELIGHT_OFFSET_Y = 1;
        public String DEFAULT_TABLE_KEY = "limelight";
    }

    public static class TesterConstants{
        public int SECONDS_TO_WAIT = 1;
        public double MOVE_POWER = 3;
        public int LED_BLINK_AMOUNT = 10;
    }

    public static class VisionConstants{
        public PIDCoefs ROTATION_SETTINGS = new PIDCoefs(0, 0, 0, 0, 0);
        public double TARGET_TIME_OUT = 0.1;

    }

}
