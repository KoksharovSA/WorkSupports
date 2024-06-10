package ru.konsist.supports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;

public class SettingsReader {
    public static String readTextFile(String fileName) {
        String result = "";
        try {
            File file = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/" + fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result += scanner.nextLine();
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<String, String> readJSONFile(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, HashMap.class);
    }
}
