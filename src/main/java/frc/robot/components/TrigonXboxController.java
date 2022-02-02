package frc.robot.components;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.constants.RobotConstants;

public class TrigonXboxController extends XboxController {
    private final JoystickButton a;
    private final JoystickButton b;
    private final JoystickButton x;
    private final JoystickButton y;
    private final JoystickButton leftBumper;
    private final JoystickButton rightBumper;
    private final JoystickButton leftStickButton;
    private final JoystickButton rightStickButton;
    private final JoystickButton backButton;
    private final JoystickButton startButton;
    private final Notifier notifier;
    private int rumbleAmount;

    public TrigonXboxController(int port) {
        super(port);
        a = new JoystickButton(this, Button.kA.value);
        b = new JoystickButton(this, Button.kB.value);
        x = new JoystickButton(this, Button.kX.value);
        y = new JoystickButton(this, Button.kY.value);
        leftBumper = new JoystickButton(this, Button.kLeftBumper.value);
        rightBumper = new JoystickButton(this, Button.kRightBumper.value);
        leftStickButton = new JoystickButton(this, Button.kLeftStick.value);
        rightStickButton = new JoystickButton(this, Button.kRightStick.value);

        backButton = new JoystickButton(this, Button.kBack.value);
        startButton = new JoystickButton(this, Button.kStart.value);
        rumbleAmount = -1;
        notifier = new Notifier(this::notifierPeriodic);
    }

    public JoystickButton getABtn() {
        return a;
    }

    public JoystickButton getBBtn() {
        return b;
    }

    public JoystickButton getXBtn() {
        return x;
    }

    public JoystickButton getYBtn() {
        return y;
    }

    public JoystickButton getLeftBumperBtn() {
        return leftBumper;
    }

    public JoystickButton getRightBumperBtn() {
        return rightBumper;
    }

    public JoystickButton getLeftStickButtonBtn() {
        return leftStickButton;
    }

    public JoystickButton getRightStickButtonBtn() {
        return rightStickButton;
    }

    public JoystickButton getBackBtn() {
        return backButton;
    }

    public JoystickButton getStartBtn() {
        return startButton;
    }

    /**
     * Right trigger value - left trigger value.
     *
     * @return The difference between the left and right triggers.
     */
    public double getDeltaTriggers() {
        return getRightTriggerAxis() - getLeftTriggerAxis();
    }

    /**
     * Set the rumble output for the HID. this method affects both motors.
     *
     * @param power The normalized value (0 to 1) to set the rumble to
     */
    public void setRumble(double power) {
        setRumble(RumbleType.kLeftRumble, power);
        setRumble(RumbleType.kRightRumble, power);
    }

    /**
     * Rumbles the controller the desired amount of times.
     *
     * @param quantity the number of times to rumble.
     */
    public void intermittentRumble(int quantity) {
        rumbleAmount = quantity * 2 - 1;
        notifier.startPeriodic(RobotConstants.DriverConstants.RUMBLE_INTERMISSION_TIME);
    }

    public double getRightY() {
        return -super.getRightY();
    }

    public double getLeftY() {
        return -super.getLeftY();
    }

    public void notifierPeriodic() {
        if(rumbleAmount == 0) {
            notifier.stop();
        }
        if(rumbleAmount >= 0) {
            if(rumbleAmount % 2 == 1)
                setRumble(1);
            else
                setRumble(0);
            rumbleAmount--;
        }
    }
}
