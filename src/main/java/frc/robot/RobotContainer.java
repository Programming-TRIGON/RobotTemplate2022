package frc.robot;

import frc.robot.constants.FieldConstants;
import frc.robot.constants.RobotConstants;
import frc.robot.utilities.DashboardController;

public class RobotContainer {
    private final DashboardController dashboardController;
    private final RobotConstants robotConstants;
    private final FieldConstants fieldConstants;

    /**
     * Add classes here
     */
    public RobotContainer() {
        dashboardController = new DashboardController();
        robotConstants = new RobotConstants();
        fieldConstants = new FieldConstants();
    }

    /**
     * initializes all commands
     */
    public void initializeCommands() {

    }

    public void updateDashboard() {
        dashboardController.update();
    }
}
