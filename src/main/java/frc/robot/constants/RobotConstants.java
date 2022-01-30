package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import frc.robot.components.Pigeon;
import frc.robot.constants.RobotMap.CAN;
import frc.robot.utilities.JsonHandler;
import frc.robot.utilities.MotorConfig;
import frc.robot.utilities.pid.PIDCoefs;

/**
 * All the constants to be uses for the robot
 */
public class RobotConstants {
    public static final LocalConstants LOCAL_CONSTANTS = JsonHandler.getConstants();
    private static final RobotMap robotMap = new RobotMap();
    public static final LimelightConstants limelightConstants = new LimelightConstants();
    public static final TesterConstants testerConstants = new TesterConstants();
    public static final VisionConstants visionConstants = new VisionConstants();
    public static final SwerveConstants swerveConstants = new SwerveConstants();
    public static final LEDConstants ledConstants = new LEDConstants();

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

    public static class SwerveConstants {
        public final boolean INVERT_GYRO = false; // Always ensure Gyro is CCW+ CW-

        /* Drivetrain Constants */
        public final double TRACK_WIDTH = 0.29765 * 2;
        public final double WHEEL_BASE = 0.29765 * 2;
        public final double WHEEL_DIAMETER = 0.1;
        public final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

        public final double DRIVE_GEAR_RATIO = (8.14 / 1.0);
        public final double ANGLE_GEAR_RATIO = (12.8 / 1.0);

        public final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(
                new Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0)
        );

        private final SupplyCurrentLimitConfiguration ANGLE_CURRENT_LIMIT = new SupplyCurrentLimitConfiguration(
                true, 25, 40,
                0.1
        );

        //TODO: Add to LocalConstants
        private final NeutralMode ANGLE_NEUTRAL_MODE =
                LOCAL_CONSTANTS.localSwerveConstants.angleNeutralMode.equals(
                        "Brake") ? NeutralMode.Brake : NeutralMode.Coast;

        public final MotorConfig ANGLE_MOTOR_CONFIG = new MotorConfig(
                2,
                0.5,
                false,
                false,
                ANGLE_NEUTRAL_MODE,
                0,
                ANGLE_CURRENT_LIMIT
        );

        private final SupplyCurrentLimitConfiguration DRIVE_CURRENT_LIMIT = new SupplyCurrentLimitConfiguration(
                true, 34, 60,
                0.1
        );

        //TODO: Add to LocalConstants
        private final NeutralMode DRIVE_NEUTRAL_MODE =
                LOCAL_CONSTANTS.localSwerveConstants.driveNeutralMode.equals(
                        "Brake") ? NeutralMode.Brake : NeutralMode.Coast;

        public final MotorConfig DRIVE_MOTOR_CONFIG = new MotorConfig(
                0.1,
                0.1,
                true,
                false,
                DRIVE_NEUTRAL_MODE,
                0,
                DRIVE_CURRENT_LIMIT
        );

        /* Angle Encoder Invert */
        public final boolean ENCODER_INVERT = false;

        /* Swerve Profiling Values */
        public final double MAX_SPEED = 4.5; //meters per second

        public final double MAX_ANGULAR_VELOCITY = 11.5;

        /* Driving Constants */
        public final double SPEED_DIVIDER = 6;

        private static CAN.SwerveMap swerveMap = robotMap.can.swerveMap;

        public final SwerveModuleConstants FRONT_LEFT_CONSTANTS = new SwerveModuleConstants(
                swerveMap.FRONT_LEFT_DRIVE_MOTOR_ID,
                swerveMap.FRONT_LEFT_ANGLE_MOTOR_ID,
                swerveMap.FRONT_LEFT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants
        );
        public final SwerveModuleConstants FRONT_RIGHT_CONSTANTS = new SwerveModuleConstants(
                swerveMap.FRONT_RIGHT_DRIVE_MOTOR_ID,
                swerveMap.FRONT_RIGHT_ANGLE_MOTOR_ID,
                swerveMap.FRONT_RIGHT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants
        );
        public final SwerveModuleConstants REAR_LEFT_CONSTANTS = new SwerveModuleConstants(
                swerveMap.REAR_LEFT_DRIVE_MOTOR_ID,
                swerveMap.REAR_LEFT_ANGLE_MOTOR_ID,
                swerveMap.REAR_LEFT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants
        );
        public final SwerveModuleConstants REAR_RIGHT_CONSTANTS = new SwerveModuleConstants(
                swerveMap.REAR_RIGHT_DRIVE_MOTOR_ID,
                swerveMap.REAR_RIGHT_ANGLE_MOTOR_ID,
                swerveMap.REAR_RIGHT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants
        );

        public static final SwerveComponents swerveComponents = new SwerveComponents();
        public static class SwerveComponents {
            public Pigeon pigeon = new Pigeon(swerveMap.PIGEON_ID);
        }
    }

    public static class LEDConstants {
        public final Spark LED_CONTROLLER = new Spark(robotMap.pwm.led.LED_CONTROLLER);
    }
}
