package frc.robot.subsystems.swerve;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.Supplier;

/**
 * A command that uses double suppliers (x, y, theta) to drive the robot, open loop.
 */
public class SupplierDriveCMD extends CommandBase {

    private SwerveSS swerveSS;
    private final Supplier<Double> x, y, theta;
    private final boolean fieldRelative;

    public SupplierDriveCMD(
            SwerveSS swerveSS, Supplier<Double> x, Supplier<Double> y, Supplier<Double> theta, boolean fieldRelative) {
        this.swerveSS = swerveSS;
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.fieldRelative = fieldRelative;
        addRequirements(swerveSS);
    }

    @Override
    public void execute() {
        swerveSS.drive(new Translation2d(x.get(), y.get()), theta.get(), fieldRelative, false);
    }

    @Override
    public void end(boolean interrupted) {
        swerveSS.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
