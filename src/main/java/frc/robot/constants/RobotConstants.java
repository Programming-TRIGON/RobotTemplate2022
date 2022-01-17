package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import frc.robot.constants.RobotMap.CAN.SwerveMap.SwerveModuleConstants;
import frc.robot.utilities.PIDCoefs;

/**
 * All the constants to be uses for the robot
 */
public class RobotConstants extends RobotMap {

    public static class LimelightConstants {
        public double DISTANCE_CALCULATION_A_COEFFICIENT = 1;
        public double DISTANCE_CALCULATION_B_COEFFICIENT = 1;
        public double DISTANCE_CALCULATION_C_COEFFICIENT = 1;
        public double LIMELIGHT_ANGLE_OFFSET = 1;
        public double LIMELIGHT_OFFSET_X = 1;
        public double LIMELIGHT_OFFSET_Y = 1;
        public String DEFAULT_TABLE_KEY = "limelight";
    }

    public static class TesterConstants {
        public int SECONDS_TO_WAIT = 1;
        public double MOVE_POWER = 3;
        public int LED_BLINK_AMOUNT = 10;
    }

    public static class VisionConstants {
        public PIDCoefs ROTATION_SETTINGS = new PIDCoefs(0, 0, 0, 0, 0);
        public double TARGET_TIME_OUT = 0.1;
    }

    public static class SwerveConstants {
        public static final int PIGEON_ID = CAN.SwerveMap.PIGEON_ID;

        public static final SwerveModuleConstants[] MODULES = CAN.SwerveMap.MODULES;

        public static final boolean INVERT_GYRO = false; // Always ensure Gyro is CCW+ CW-

        /* Drivetrain Constants */
        public static final double TRACK_WIDTH = 0.29765 * 2;
        public static final double WHEEL_BASE = 0.29765 * 2;
        public static final double WHEEL_DIAMETER = 0.1;
        public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

        public static final double ANGLE_OPEN_LOOP_RAMP = 2;
        public static final double ANGLE_CLOSED_LOOP_RAMP = .5;

        public static final double DRIVE_OPEN_LOOP_RAMP = 0.1;
        public static final double DRIVE_CLOSED_LOOP_RAMP = 0.1;

        public static final double DRIVE_GEAR_RATIO = (8.14 / 1.0);
        public static final double ANGLE_GEAR_RATIO = (12.8 / 1.0);

        public static final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(
                new Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0)
        );

        /* Swerve Current Limiting */
        public static final int ANGLE_CONTINUOUS_CURRENT_LIMIT = 25;
        public static final int ANGLE_PEAK_CURRENT_LIMIT = 40;
        public static final double ANGLE_PEAK_CURRENT_DURATION = 0.1;
        public static final boolean ANGLE_ENABLE_CURRENT_LIMIT = true;

        public static final SupplyCurrentLimitConfiguration ANGLE_CURRENT_LIMIT = new SupplyCurrentLimitConfiguration(
                ANGLE_ENABLE_CURRENT_LIMIT, ANGLE_CONTINUOUS_CURRENT_LIMIT, ANGLE_PEAK_CURRENT_LIMIT,
                ANGLE_PEAK_CURRENT_DURATION
        );

        public static final int DRIVE_CONTINUOUS_CURRENT_LIMIT = 35;
        public static final int DRIVE_PEAK_CURRENT_LIMIT = 60;
        public static final double DRIVE_PEAK_CURRENT_DURATION = 0.1;
        public static final boolean DRIVE_ENABLE_CURRENT_LIMIT = true;

        public static final SupplyCurrentLimitConfiguration DRIVE_CURRENT_LIMIT = new SupplyCurrentLimitConfiguration(
                DRIVE_ENABLE_CURRENT_LIMIT, DRIVE_CONTINUOUS_CURRENT_LIMIT, DRIVE_PEAK_CURRENT_LIMIT,
                DRIVE_PEAK_CURRENT_DURATION
        );

        /* Swerve Profiling Values */
        public static final double MAX_SPEED = 4.5; //meters per second
        public static final double MAX_ANGULAR_VELOCITY = 11.5;

        /* Neutral Modes */ //TODO: Add to LocalConstants
        public static final NeutralMode ANGLE_NEUTRAL_MODE =
                LOCAL_CONSTANTS.localSwerveConstants.angleNeutralMode.equals(
                        "Brake") ? NeutralMode.Brake : NeutralMode.Coast;
        public static final NeutralMode DRIVE_NEUTRAL_MODE =
                LOCAL_CONSTANTS.localSwerveConstants.driveNeutralMode.equals(
                        "Brake") ? NeutralMode.Brake : NeutralMode.Coast;

        /* Motor Inverts */
        public static final boolean DRIVE_MOTOR_INVERT = true;
        public static final boolean ANGLE_MOTOR_INVERT = false;

        /* Angle Encoder Invert */
        public static final boolean ENCODER_INVERT = false;

        /* Driving Constants */
        public static final double SPEED_DIVIDER = 6;
    }
}
