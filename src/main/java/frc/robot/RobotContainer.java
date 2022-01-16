package frc.robot;

import frc.robot.constants.fields.HomeField;
import frc.robot.constants.robots.RobotA;
import frc.robot.utilities.DashboardController;

public class RobotContainer {
    private final RobotA robotConstants;
    private final HomeField fieldConstants;
    private final DashboardController dashboardController;

    /**
     * Add classes here
     */
    public RobotContainer() {
        robotConstants = new RobotA();
        fieldConstants = new HomeField();
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
