package frc.robot.constants;

import com.google.gson.Gson;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//Static class, JsonHandler, uses gson to read and write the constants.json file
public class JsonHandler{
    private static final String path = Filesystem.getOperatingDirectory() + "/constants.json";
    private static final Gson gson = new Gson();

    /**
     * Reads the constants.json file using gson and returns the constants object.
     * If the file does not exist, it generates it with blank values, and notifies the user.
     *
     * @return The constants object
     */
    public static LocalConstants getConstants(){
        System.out.println("Reading constants file from " + path);
        try{
            if(Files.exists(Paths.get(path))){
                return gson.fromJson(new String(Files.readAllBytes(Paths.get(path))), LocalConstants.class);
            } else{
                Files.createFile(Paths.get(path));
                System.out.println("Constants file not found, creating new file at " + path);
                write();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return new LocalConstants();
    }

    /**
     * Writes the constants to the constants.json file using gson
     *
     * @param constants The constants to write
     */
    public static void write(LocalConstants constants){
        try{
            String json = gson.toJson(constants);
            System.out.println(json);
            Files.write(Paths.get(path), json.getBytes(StandardCharsets.UTF_8));
            System.out.println("Constants file written to " + path);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the default constants to the constants.json file using gson
     */
    public static void write(){
        write(new LocalConstants());
    }
}