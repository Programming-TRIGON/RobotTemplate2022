package frc.robot.subsystems.led;

/**
 * Color enum for the LED class. 
 * The enum value represent the power needed for the color on Spark controller (PWM).
 */
public enum LEDColor {
    Red(0.61),
    Orange(0.65),
    Gold(0.67),
    Yellow(0.69),
    Green(0.77),
    Aqua(0.81),
    Blue(0.87),
    White(0.93),
    Black(0.99),
    Random(0),
    Off(0);

    private final double value;

    LEDColor(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
