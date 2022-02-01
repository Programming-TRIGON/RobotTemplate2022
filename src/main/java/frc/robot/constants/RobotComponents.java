package frc.robot.constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import frc.robot.components.Pigeon;
import frc.robot.components.TrigonTalonSRX;
import frc.robot.utilities.MotorConfig;
import frc.robot.utilities.pid.PIDFTalonFX;

public class RobotComponents {
    protected static class SwerveComponents {
        public static final Pigeon PIGEON = new Pigeon(RobotMap.CAN.SwerveMap.PIGEON_ID);

        // configs
        private static final MotorConfig ANGLE_MOTOR_CONFIG = new MotorConfig().
                inverted(false).
                sensorPhase(false).
                withOpenLoopRampRate(2).
                withClosedLoopRampRate(0.5).
                brake().
                withCurrentLimit(new SupplyCurrentLimitConfiguration(
                        true,
                        25,
                        40,
                        0.1
                ));
        private static final MotorConfig DRIVE_MOTOR_CONFIG = new MotorConfig().
                inverted(true).
                sensorPhase(false).
                withOpenLoopRampRate(0.1).
                withClosedLoopRampRate(0.1).
                brake().
                withCurrentLimit(new SupplyCurrentLimitConfiguration(
                        true,
                        34,
                        60,
                        0.1
                ));
        private static final MotorConfig ANGLE_ENCODER_CONFIG = new MotorConfig().
                withFeedbackNotContinuous(true).
                withFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Absolute);

        /* Front Left */
        public static final PIDFTalonFX FRONT_LEFT_ANGLE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.FRONT_LEFT_ANGLE_MOTOR_ID, ANGLE_MOTOR_CONFIG.withPID(
                        RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.angleCoefs).
                withFeedbackDevice(FeedbackDevice.IntegratedSensor).
                withRemoteSensorSource(
                        RobotMap.CAN.SwerveMap.FRONT_LEFT_ANGLE_MOTOR_ID, RemoteSensorSource.TalonSRX_SelectedSensor));
        public static final PIDFTalonFX FRONT_LEFT_DRIVE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.FRONT_LEFT_DRIVE_MOTOR_ID, DRIVE_MOTOR_CONFIG.withPID(
                RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.frontLeftModuleConstants.driveCoefs));
        public static final TrigonTalonSRX FRONT_LEFT_ENCODER = new TrigonTalonSRX(
                RobotMap.CAN.SwerveMap.FRONT_LEFT_ANGLE_ENCODER_ID, ANGLE_ENCODER_CONFIG);

        /* Front Right */
        public static final PIDFTalonFX FRONT_RIGHT_ANGLE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.FRONT_RIGHT_ANGLE_MOTOR_ID, ANGLE_MOTOR_CONFIG.withPID(
                        RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.angleCoefs).
                withFeedbackDevice(FeedbackDevice.IntegratedSensor).
                withRemoteSensorSource(
                        RobotMap.CAN.SwerveMap.FRONT_RIGHT_ANGLE_MOTOR_ID, RemoteSensorSource.TalonSRX_SelectedSensor));
        public static final PIDFTalonFX FRONT_RIGHT_DRIVE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.FRONT_RIGHT_DRIVE_MOTOR_ID, DRIVE_MOTOR_CONFIG.withPID(
                RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.frontRightModuleConstants.driveCoefs));
        public static final TrigonTalonSRX FRONT_RIGHT_ENCODER = new TrigonTalonSRX(
                RobotMap.CAN.SwerveMap.FRONT_RIGHT_ANGLE_ENCODER_ID, ANGLE_ENCODER_CONFIG);

        /* Rear Left */
        public static final PIDFTalonFX REAR_LEFT_ANGLE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.REAR_LEFT_ANGLE_MOTOR_ID, ANGLE_MOTOR_CONFIG.withPID(
                        RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.angleCoefs).
                withFeedbackDevice(FeedbackDevice.IntegratedSensor).
                withRemoteSensorSource(
                        RobotMap.CAN.SwerveMap.REAR_LEFT_ANGLE_MOTOR_ID, RemoteSensorSource.TalonSRX_SelectedSensor));
        public static final PIDFTalonFX REAR_LEFT_DRIVE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.REAR_LEFT_DRIVE_MOTOR_ID, DRIVE_MOTOR_CONFIG.withPID(
                RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.rearLeftModuleConstants.driveCoefs));
        public static final TrigonTalonSRX REAR_LEFT_ENCODER = new TrigonTalonSRX(
                RobotMap.CAN.SwerveMap.REAR_LEFT_ANGLE_ENCODER_ID, ANGLE_ENCODER_CONFIG);

        /* Rear Right */
        public static final PIDFTalonFX REAR_RIGHT_ANGLE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.REAR_RIGHT_ANGLE_MOTOR_ID, ANGLE_MOTOR_CONFIG.withPID(
                        RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.angleCoefs).
                withFeedbackDevice(FeedbackDevice.IntegratedSensor).
                withRemoteSensorSource(
                        RobotMap.CAN.SwerveMap.REAR_RIGHT_ANGLE_MOTOR_ID, RemoteSensorSource.TalonSRX_SelectedSensor));
        public static final PIDFTalonFX REAR_RIGHT_DRIVE_MOTOR = new PIDFTalonFX(
                RobotMap.CAN.SwerveMap.REAR_RIGHT_DRIVE_MOTOR_ID, DRIVE_MOTOR_CONFIG.withPID(
                RobotConstants.LOCAL_CONSTANTS.localSwerveConstants.modules.rearRightModuleConstants.driveCoefs));
        public static final TrigonTalonSRX REAR_RIGHT_ENCODER = new TrigonTalonSRX(
                RobotMap.CAN.SwerveMap.REAR_RIGHT_ANGLE_ENCODER_ID, ANGLE_ENCODER_CONFIG);
    }
}
