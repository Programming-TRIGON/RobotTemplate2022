package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import frc.robot.components.Pigeon;
import frc.robot.constants.RobotMap.CAN;
import frc.robot.utilities.JsonHandler;
import frc.robot.utilities.pid.PIDCoefs;

/**
 * All the constants to be uses for the robot
 */
public class RobotConstants {
    public static final LocalConstants LOCAL_CONSTANTS = JsonHandler.getConstants();

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

        public static final SwerveModuleConstants FRONT_LEFT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.FRONT_LEFT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.FRONT_LEFT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.FRONT_LEFT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.encoderOffset,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.angleCoefs,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.driveCoefs
        );
        public static final SwerveModuleConstants FRONT_RIGHT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.FRONT_RIGHT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.FRONT_RIGHT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.FRONT_RIGHT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.encoderOffset,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.angleCoefs,
                LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.driveCoefs
        );
        public static final SwerveModuleConstants REAR_LEFT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.REAR_LEFT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.REAR_LEFT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.REAR_LEFT_ANGLE_ENCODER_ID,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.encoderOffset,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.angleCoefs,
                LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.driveCoefs
        );
        public static final SwerveModuleConstants REAR_RIGHT_CONSTANTS = new SwerveModuleConstants(
                CAN.SwerveMap.REAR_RIGHT_DRIVE_MOTOR_ID,
                CAN.SwerveMap.REAR_RIGHT_ANGLE_MOTOR_ID,
                CAN.SwerveMap.REAR_RIGHT_ANGLE_ENCODER_ID,
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

        public static class SwerveComponents {
            public static Pigeon pigeon = new Pigeon(CAN.SwerveMap.PIGEON_ID);
        }
    }
}
