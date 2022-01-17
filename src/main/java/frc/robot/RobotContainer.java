package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.subsystems.swerve.SupplierDriveCMD;
import frc.robot.subsystems.swerve.SwerveSS;
import frc.robot.utilities.DashboardController;
import frc.robot.utilities.TrigonController;

public class RobotContainer {
    private final DashboardController dashboardController;
    private final TrigonController driverXbox;

    // Subsystems
    public static final SwerveSS swerveSS = new SwerveSS();

    // Commands
    public static SupplierDriveCMD driveWithXboxCMD;

    /**
     * Add classes here
     */
    public RobotContainer() {
        dashboardController = new DashboardController();
        driverXbox = new TrigonController(0);

        initializeCommands();
    }

    /**
     * initializes all commands
     */
    public void initializeCommands() {
        driveWithXboxCMD = new SupplierDriveCMD(
                () -> driverXbox.getX(GenericHID.Hand.kLeft),
                () -> driverXbox.getY(GenericHID.Hand.kLeft),
                () -> driverXbox.getX(GenericHID.Hand.kRight),
                true);
    }

    public void updateDashboard() {
        dashboardController.update();
    }
}
