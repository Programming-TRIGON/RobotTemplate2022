package frc.robot.constants;

import com.google.gson.Gson;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Static class, JsonHandler, uses gson to read and write the constants.json file
public class JsonHandler {
    private static final String path = Filesystem.getDeployDirectory() + "/constants.json";
    private static final Gson gson = new Gson();

    /**
     * Reads the constants.json file using gson and returns the raw string
     *
     * @return The raw string of the constants.json file
     */
    public static String getRaw() {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads the constants.json file using gson and returns the constants object
     *
     * @return The constants object
     */
    public static LocalConstants getConstants() {
        return gson.fromJson(getRaw(), LocalConstants.class);
    }

    /**
     * Writes the constants to the constants.json file using gson
     *
     * @param constants The constants to write
     */
    public static void write(LocalConstants constants) {
        try {
            Files.write(Paths.get(path), gson.toJson(constants).getBytes());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}