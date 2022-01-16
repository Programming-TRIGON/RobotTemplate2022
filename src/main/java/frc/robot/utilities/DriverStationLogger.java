package frc.robot.utilities;

/**
 * Driver station logger is class for logging to the DS. Logs can be found in
 * the driver station log file viewer, at the Event List label.
 * 
 * @see <a
 *      href=https://docs.wpilib.org/en/latest/docs/software/driverstation/driver-station-log-viewer.html>Driver
 *      Station Log File Viewer</a>
 */
public class DriverStationLogger {

    public static void logToDS(String log) {
        System.out.println("DS log: " + log);
    }

    public static void logErrorToDS(String errorLog) {
        System.err.println("!!!!!!!!!!!!!!!!!!! DS error log: " + errorLog + " !!!!!!!!!!!!!!!!!!!");
    }
}
