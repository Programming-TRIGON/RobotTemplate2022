package frc.robot.constants;

import frc.robot.utilities.pid.PIDFCoefs;
import frc.robot.constants.LocalConstants.LocalSwerveConstants.LocalSwerveModules.LocalSwerveModuleConstants;

public class SwerveModuleConstants {
    public final int driveMotorID;
    public final int angleMotorID;
    public final int encoderID;
    public final double encoderOffset;
    public final PIDFCoefs anglePIDFCoefs;
    public final PIDFCoefs drivePIDFCoefs;

    public SwerveModuleConstants(
            int driveMotorID, int angleMotorID, int encoderID,
            LocalSwerveModuleConstants localSwerveModuleConstants) {
        this.driveMotorID = driveMotorID;
        this.angleMotorID = angleMotorID;
        this.encoderID = encoderID;
        this.encoderOffset = localSwerveModuleConstants.encoderOffset;
        this.anglePIDFCoefs = localSwerveModuleConstants.angleCoefs;
        this.drivePIDFCoefs = localSwerveModuleConstants.driveCoefs;
    }
}

