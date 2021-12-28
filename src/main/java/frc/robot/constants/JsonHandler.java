package frc.robot.constants;

import com.google.gson.Gson;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Static class, JsonHandler, uses gson to read and write the constants.json file
public class JsonHandler {
    private static String path = Filesystem.getDeployDirectory() + "/constants.json";
    private static Gson gson = new Gson();

    //Read the constants.json file
    public static String getRaw() {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalConstants getConstants() {
        return gson.fromJson(getRaw(), LocalConstants.class);
    }

    //Write the constants.json file
    public static void write(LocalConstants constants) {
        try {
            Files.write(Paths.get(path), gson.toJson(constants).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}