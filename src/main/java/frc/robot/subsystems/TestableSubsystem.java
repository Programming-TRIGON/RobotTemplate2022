package frc.robot.subsystems;

import frc.robot.constants.RobotConstants.TesterConstants;

public interface TestableSubsystem extends MovableSubsystem, ReadableSubsystem {
    default void test() {
        move(TesterConstants.DEF_MOVE_POWER);
    }
}
