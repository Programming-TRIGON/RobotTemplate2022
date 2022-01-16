package frc.robot;


import frc.robot.subsystems.swerve.Swerve;
import frc.robot.utilities.DashboardController;

public class RobotContainer{
    private final DashboardController dashboardController;
    private final Swerve swerve;

    /**
     * Add classes here
     */
    public RobotContainer(){
        dashboardController = new DashboardController();
        swerve = new Swerve();

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
