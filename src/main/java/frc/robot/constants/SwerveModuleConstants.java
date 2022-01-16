package frc.robot.constants;

import frc.robot.utilities.PIDCoefs;

public class SwerveModuleConstants{
    public final int moduleNumber;
    public final int driveMotorID;
    public final int angleMotorID;
    public final int encoderID;
    public final double encoderOffset;
    public final PIDCoefs anglePIDCoefs, drivePIDCoefs;


    /**
     * Swerve Module Constants to be used when creating swerve modules.
     *
     * @param moduleNumber  The number of the module.
     * @param driveMotorID  The ID of the drive motor.
     * @param angleMotorID  The ID of the angle motor.
     * @param encoderID     The ID of the encoder.
     * @param encoderOffset The offset of the angle encoder.
     * @param anglePIDCoefs The PID coefficients for the angle motor.
     * @param drivePIDCoefs The PID coefficients for the drive motor.
     */
    public SwerveModuleConstants(int moduleNumber, int driveMotorID, int angleMotorID, int encoderID, double encoderOffset, PIDCoefs anglePIDCoefs, PIDCoefs drivePIDCoefs){
        this.moduleNumber = moduleNumber;
        this.driveMotorID = driveMotorID;
        this.angleMotorID = angleMotorID;
        this.encoderID = encoderID;
        this.encoderOffset = encoderOffset;
        this.anglePIDCoefs = anglePIDCoefs;
        this.drivePIDCoefs = drivePIDCoefs;
    }
}
