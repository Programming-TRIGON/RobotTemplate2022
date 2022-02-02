package frc.robot;

import frc.robot.components.TrigonXboxController;
import frc.robot.constants.RobotConstants;
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
        driverXbox = new TrigonXboxController(RobotConstants.DriverConstants.XBOX_PORT);

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
        Supplier<Double> x, y, rot;
        if(RobotConstants.DriverConstants.SQUARED_CONTROLLER_DRIVING) {
            x = () -> Math.pow(driverXbox.getLeftX(), 2);
            y = () -> Math.pow(driverXbox.getLeftY(), 2);
            rot = () -> Math.pow(driverXbox.getRightX(), 2);
        } else {
            x = driverXbox::getLeftX;
            y = driverXbox::getLeftY;
            rot = driverXbox::getRightX;
        }

        driveWithXboxCMD = new SupplierDriveCMD(
                swerveSS,
                x,
                y,
                rot,
                true);
        swerveSS.setDefaultCommand(driveWithXboxCMD);
    }

    public void periodic() {
        dashboardController.update();
    }
}
