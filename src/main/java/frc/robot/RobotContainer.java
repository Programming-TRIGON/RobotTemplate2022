package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.subsystems.swerve.SupplierDriveCMD;
import frc.robot.subsystems.swerve.SwerveSS;
import frc.robot.utilities.DashboardController;
import frc.robot.utilities.TrigonController;

public class RobotContainer {
    private final DashboardController dashboardController;
    private final TrigonController driverXbox;

    // Subsystems
    private final SwerveSS swerveSS;

    // Commands
    private SupplierDriveCMD driveWithXboxCMD;

    /**
     * Add classes here
     */
    public RobotContainer() {
        dashboardController = new DashboardController();
        driverXbox = new TrigonController(0);

        swerveSS = new SwerveSS();

        initializeCommands();
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
    }

    public void updateDashboard() {
        dashboardController.update();
    }
}
