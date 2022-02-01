package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.components.TrigonXboxController;
import frc.robot.constants.RobotConstants;
import frc.robot.subsystems.swerve.SupplierDriveCMD;
import frc.robot.subsystems.swerve.SwerveSS;
import frc.robot.utilities.DashboardController;

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
        driveWithXboxCMD = new SupplierDriveCMD(
                swerveSS,
                () -> driverXbox.getX(Hand.kLeft),
                () -> driverXbox.getY(Hand.kLeft),
                () -> driverXbox.getX(Hand.kRight),
                true);
        swerveSS.setDefaultCommand(driveWithXboxCMD);
    }

    public void periodic() {
        dashboardController.update();
    }
}
