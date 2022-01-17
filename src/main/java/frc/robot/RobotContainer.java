package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.subsystems.swerve.SupplierDriveCMD;
import frc.robot.subsystems.swerve.Swerve;
import frc.robot.utilities.DashboardController;
import frc.robot.utilities.TrigonController;

public class RobotContainer {
    // Subsystems
    public static final Swerve swerve = new Swerve();

    // Commands
    public static SupplierDriveCMD teleopDrive;

    private final DashboardController dashboardController;
    private final TrigonController controller;

    /**
     * Add classes here
     */
    public RobotContainer() {
        dashboardController = new DashboardController();
        controller = new TrigonController(0);

        initializeCommands();
    }

    /**
     * initializes all commands
     */
    public void initializeCommands() {
        teleopDrive = new SupplierDriveCMD(
                () -> controller.getX(GenericHID.Hand.kLeft),
                () -> controller.getY(GenericHID.Hand.kLeft),
                () -> controller.getX(GenericHID.Hand.kRight),
                true
        );
    }

    public void updateDashboard() {
        dashboardController.update();
    }
}
