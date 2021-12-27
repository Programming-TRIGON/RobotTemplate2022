package frc.robot.constants;

import com.google.gson.Gson;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Singleton class, JsonHandler, uses gson to read and write the constants.json file
public class JsonHandler {
    private static JsonHandler instance = null;
    private static String path = Filesystem.getDeployDirectory() + "/constants.json";
    private static Gson gson = new Gson();
    private static LocalConstants constants;

    //Private constructor
    private JsonHandler() {
        constants = gson.fromJson(get(), LocalConstants.class);
    }

    //Get the instance of the class
    public static JsonHandler getInstance() {
        if (instance == null) {
            instance = new JsonHandler();
        }
        return instance;
    }

    //Get the constants
    public static LocalConstants getConstants() {
        return constants;
    }

    //Read the constants.json file
    private static String get() {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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