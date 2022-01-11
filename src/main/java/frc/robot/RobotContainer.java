package frc.robot;


import frc.robot.utilities.DashboardController;

public class RobotContainer{
    private final DashboardController dashboardController;

    /**
     * Add classes here
     */
    public RobotContainer(){
        dashboardController = new DashboardController();
    }

    /**
     * initializes all commands
     */
    public void initializeCommands(){

    }

    public void updateDashboard(){
        dashboardController.update();
    }
}
