package ru.konsist.supports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SettingsTgBot {
    private static SettingsTgBot settingsTgBotApp;
    private String telegramBotName;
    private String telegramBotToken;
    private String jenkinsHost;
    private String jenkinsPort;

    public String getJenkinsHost() {
        return jenkinsHost;
    }

    public String getJenkinsPort() {
        return jenkinsPort;
    }

    public String getTelegramBotName() {
        return telegramBotName;
    }

    public String getTelegramBotToken() {
        return telegramBotToken;
    }


    public static SettingsTgBot getInstance() {
        if (settingsTgBotApp == null) {
            settingsTgBotApp = new SettingsTgBot();
        }
        return settingsTgBotApp;
    }

    private SettingsTgBot() {
        try {
            Map<String, String> mapSettings = readJSONFile(readTextFile("Settings.json"));
            for (Map.Entry<String, String> entry : mapSettings.entrySet()) {
                switch (entry.getKey()) {
                    case ("telegramBotName"):
                        telegramBotName = entry.getValue();
                        break;
                    case ("telegramBotToken"):
                        telegramBotToken = entry.getValue();
                        break;
                    case ("jenkinsHost"):
                        jenkinsHost = entry.getValue();
                        break;
                    case ("jenkinsPort"):
                        jenkinsPort = entry.getValue();
                        break;
                    default:
                        break;
                }
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
        }
    }

    public void updateSettings() {
        try {
            Map<String, String> mapSettings = readJSONFile(readTextFile("Settings.json"));
            for (Map.Entry<String, String> entry : mapSettings.entrySet()) {
                switch (entry.getKey()) {
                    case ("telegramBotName"):
                        telegramBotName = entry.getValue();
                        break;
                    case ("telegramBotToken"):
                        telegramBotToken = entry.getValue();
                        break;
                    default:
                        break;
                }
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
        }
    }

    private static String readTextFile(String fileName) {
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

    private static Map<String, String> readJSONFile(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, HashMap.class);
    }
}
