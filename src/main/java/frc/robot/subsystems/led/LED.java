package frc.robot.subsystems.led;

import java.util.Random;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotConstants;

public class LED extends SubsystemBase {
  private static final double BLINK_TIME = 0.2;
  private Spark ledController;
  private LEDColor currentColor;
  private LEDColor blinkColor;
  private LEDColor lastColorBeforeBlink;
  private int blinkingAmount;
  private Notifier notifier;
  private Random rand;

  /**
   * Creates a new LED subsystem for Rev robotics Led controller and color
   * changing.
   */
  public LED(RobotConstants.PWM.LED ledMap) {
    ledController = new Spark(ledMap.LED_CONTROLLER);
    currentColor = LEDColor.Off;
    blinkingAmount = -1;
    notifier = new Notifier(this::notifierPeriodic);
    rand = new Random();

    notifier.startPeriodic(BLINK_TIME);
  }

  public void setColor(LEDColor color) {
    currentColor = color;
    blinkingAmount = -1;
    setControllerPower(color.getValue());
  }

  public void setControllerPower(double value) {
    ledController.set(value);
  }

  public void turnOffLED() {
    setColor(LEDColor.Off);
  }

  public LEDColor getCurrentColor() {
    return currentColor;
  }

  /**
   * Blinks the LED with a certain color for several times.
   * 
   * @param color    the color to blink
   * @param quantity the number of times to blink
   */
  public void blinkColor(LEDColor color, int quantity) {
    lastColorBeforeBlink = getCurrentColor();
    turnOffLED();
    blinkColor = color;
    blinkingAmount = quantity * 2;
  }

  public boolean isLedOn() {
    return ledController.get() != 0;
  }

  public void notifierPeriodic() {
    if (blinkingAmount == 0)
      setColor(lastColorBeforeBlink);
    if (blinkingAmount > 0) {
      LEDColor colorToSet;
      if (blinkingAmount % 2 == 1)
        colorToSet = LEDColor.Off;
      else
        colorToSet = blinkColor;
      setControllerPower(colorToSet.getValue());
      currentColor = colorToSet;
      blinkingAmount--;
    }
  }

  public void setAllianceColor() {
    if (DriverStation.getInstance().getAlliance().equals(Alliance.Blue))
      setColor(LEDColor.Blue);
    else
      setColor(LEDColor.Red);
  }

  public void setRandomPattern() {
    currentColor = LEDColor.Random;
    blinkingAmount = -1;
    setControllerPower(getRandomPattern());
  }

  /**
   * @return random number between -0.05 to -0.99 in jumps of 0.02
   */
  private double getRandomPattern() {
    double rand = 0.1 * this.rand.nextInt(10);
    int odd = randomOddNumber();
    while (odd < 5 && rand == 0.0) {
      odd = randomOddNumber();
    }
    return -(rand + odd * 0.01);
  }

  /**
   * @return random odd number between 0 and 9
   */
  private int randomOddNumber() {
    int rand = this.rand.nextInt(10);
    return rand % 2 == 0 ? rand + 1 : rand;
  }
}
