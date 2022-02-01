package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import frc.robot.constants.RobotMap.CAN;
import frc.robot.constants.RobotMap.PWM;
import frc.robot.utilities.JsonHandler;
import frc.robot.utilities.MotorConfig;
import frc.robot.utilities.pid.PIDCoefs;

/**
 * All the constants to be uses for the robot
 */
public class RobotConstants {
    public static final int DEF_CAN_TIMEOUT = 30;
    private static final LocalConstants LOCAL_CONSTANTS = JsonHandler.getConstants();

    public static class LimelightConstants {
        public static final double DISTANCE_CALCULATION_A_COEFFICIENT = 1;
        public static final double DISTANCE_CALCULATION_B_COEFFICIENT = 1;
        public static final double DISTANCE_CALCULATION_C_COEFFICIENT = 1;
        public static final double LIMELIGHT_ANGLE_OFFSET = 1;
        public static final double LIMELIGHT_OFFSET_X = 1;
        public static final double LIMELIGHT_OFFSET_Y = 1;
        public static final String DEFAULT_TABLE_KEY = "limelight";
    }

    public static class TesterConstants {
        public static final int SECONDS_TO_WAIT = 1;
        public static final double MOVE_POWER = 3;
        public static final int LED_BLINK_AMOUNT = 10;
    }

    public static class VisionConstants {
        public static final PIDCoefs ROTATION_SETTINGS = new PIDCoefs(0, 0, 0, 0, 0);
        public static final double TARGET_TIME_OUT = 0.1;
    }

    public static class SwerveConstants {
        public static final boolean INVERT_GYRO = false; // Always ensure Gyro is CCW+ CW-
        public static final int PIGEON_ID = CAN.SwerveMap.PIGEON_ID;

        /* Drivetrain Constants */
        public static final double TRACK_WIDTH = 0.29765 * 2;
        public static final double WHEEL_BASE = 0.29765 * 2;
        public static final double WHEEL_DIAMETER = 0.1;
        public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

        public static final double DRIVE_GEAR_RATIO = (8.14 / 1.0);
        public static final double ANGLE_GEAR_RATIO = (12.8 / 1.0);

        public static final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(
                new Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0)
        );

        public static final double MAX_SPEED = 4.5; //meters per second
        public static final double MAX_ANGULAR_VELOCITY = 11.5;

        public static final SwerveModuleConstants FRONT_LEFT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.FRONT_LEFT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.FRONT_LEFT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.FRONT_LEFT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants
        );
        public static final SwerveModuleConstants FRONT_RIGHT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.FRONT_RIGHT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.FRONT_RIGHT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.FRONT_RIGHT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants
        );
        public static final SwerveModuleConstants REAR_LEFT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.REAR_LEFT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.REAR_LEFT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.REAR_LEFT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants
        );
        public static final SwerveModuleConstants REAR_RIGHT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.REAR_RIGHT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.REAR_RIGHT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.REAR_RIGHT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants
        );
        public static final MotorConfig ANGLE_MOTOR_CONFIG = new MotorConfig().
                withInverted(false).
                withSensorInverted(false).
                withOpenLoopRampRate(2).
                withClosedLoopRampRate(0.5).
                withNeutralMode(NeutralMode.valueOf(LOCAL_CONSTANTS.localSwerveConstants.angleNeutralMode)).
                withCurrentLimitConfig(new SupplyCurrentLimitConfiguration(
                        true,
                        25,
                        40,
                        0.1
                ));
        public static final MotorConfig DRIVE_MOTOR_CONFIG = new MotorConfig().
                withInverted(true).
                withSensorInverted(false).
                withOpenLoopRampRate(0.1).
                withClosedLoopRampRate(0.1).
                withNeutralMode(NeutralMode.valueOf(LOCAL_CONSTANTS.localSwerveConstants.driveNeutralMode)).
                withCurrentLimitConfig(new SupplyCurrentLimitConfiguration(
                        true,
                        34,
                        60,
                        0.1
                ));

        /* Driver Constants */
        public static final double SPEED_DIVIDER = LOCAL_CONSTANTS.localDriverConstants.speedDivider;
    }

    public static class LedConstants {
        public static final int CONTROLLER_PORT = PWM.LED.CONTROLLER_PORT;
    }
}
