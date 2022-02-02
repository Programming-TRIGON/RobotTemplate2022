package frc.robot;

import frc.robot.components.TrigonXboxController;
import frc.robot.constants.RobotConstants.*;
import frc.robot.subsystems.swerve.SupplierDriveCMD;
import frc.robot.subsystems.swerve.SwerveSS;
import frc.robot.utilities.DashboardController;

import java.util.function.Supplier;

public class RobotContainer {
    private final DashboardController dashboardController;
    private final TrigonXboxController driverXbox;

    // Subsystems
    private SwerveSS swerveSS;

    // Commands
    private SupplierDriveCMD driveWithXboxCMD;

    /**
     * Add classes here
     */
    public RobotContainer() {
        dashboardController = new DashboardController();
        driverXbox = new TrigonXboxController(DriverConstants.XBOX_PORT);

        initializeSubsystems();
        initializeCommands();
    }

    /**
     * Initializes all subsystems
     */
    private void initializeSubsystems() {
        swerveSS = new SwerveSS();
    }

    /**
     * initializes all commands
     */
    public void initializeCommands() {
        boolean squared = DriverConstants.SQUARED_CONTROLLER_DRIVING;
        driveWithXboxCMD = new SupplierDriveCMD(
                swerveSS,
                squared ? ()->Math.pow(driverXbox.getLeftX(), 2) : driverXbox::getLeftX,
                squared ? ()->Math.pow(driverXbox.getLeftY(), 2) : driverXbox::getLeftY,
                squared ? ()->Math.pow(driverXbox.getRightX(), 2) : driverXbox::getRightX,
                true);

        swerveSS.setDefaultCommand(driveWithXboxCMD);
    }

    public void periodic() {
        dashboardController.update();
    }
}
