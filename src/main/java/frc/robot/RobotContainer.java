package frc.robot;

import frc.robot.constants.FieldConstants;
import frc.robot.constants.RobotConstants;
import frc.robot.utilities.DashboardController;

public class RobotContainer {
    private final RobotConstants robotConstants;
    private final FieldConstants fieldConstants;
    private final DashboardController dashboardController;

    /**
     * Add classes here
     */
    public RobotContainer() {
        robotConstants = new RobotConstants();
        fieldConstants = new FieldConstants();
        dashboardController = new DashboardController();
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
