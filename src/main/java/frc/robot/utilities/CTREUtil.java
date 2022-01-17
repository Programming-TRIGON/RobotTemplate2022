package frc.robot.utilities;

import com.ctre.phoenix.ErrorCode;
import frc.robot.Robot;

import java.util.function.Supplier;

public class CTREUtil {
    // Recursive function to check if the error code from the errorCodeSupplier is OK.
    // If not, print to the console, and try again, up to attempts times.
    public static void checkError(Supplier<ErrorCode> errorCodeSupplier, int attempts) {
        // If this is a simulation, skip this and let the user know
        if(Robot.isSimulation()) {
            System.out.println("Skipping error check because this is a simulation");
            return;
        }
        ErrorCode errorCode = errorCodeSupplier.get();
        if(errorCode != ErrorCode.OK) {
            if(attempts > 0) {
                System.out.println("Error: " + errorCode.toString() + ". Retrying...");
                checkError(errorCodeSupplier, attempts - 1);
            } else {
                System.out.println("Error: " + errorCode.toString() + ". Giving up.");
            }
        }
    }
}
