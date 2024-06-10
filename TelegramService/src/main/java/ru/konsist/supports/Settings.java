package ru.konsist.supports;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class Settings {
    private static Settings settingsApp;

    private String telegramBotName;
    private String telegramBotToken;
    private String telegramBotServiceIP;
    private String telegramBotServicePort;

    public String getTelegramBotName() {
        return telegramBotName;
    }

    public String getTelegramBotToken() {
        return telegramBotToken;
    }

    public String getTelegramBotServiceIP() {
        return telegramBotServiceIP;
    }

    public String getTelegramBotServicePort() {
        return telegramBotServicePort;
    }

    public static Settings getInstance(){
        if (settingsApp == null) {
            settingsApp = new Settings();
        }
        return settingsApp;
    }

    private Settings() {
        try {
            Map<String, String> mapSettings = SettingsReader.readJSONFile(SettingsReader.readTextFile("Settings.json"));
            for (Map.Entry<String, String> entry : mapSettings.entrySet()) {
                switch (entry.getKey()) {
                    case ("telegramBotName"):
                        telegramBotName = entry.getValue();
                        break;
                    case ("telegramBotToken"):
                        telegramBotToken = entry.getValue();
                        break;
                    case ("telegramBotServiceIP"):
                        telegramBotServiceIP = entry.getValue();
                        break;
                    case ("telegramBotServicePort"):
                        telegramBotServicePort = entry.getValue();
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
            Map<String, String> mapSettings = SettingsReader.readJSONFile(SettingsReader.readTextFile("Settings.json"));
            for (Map.Entry<String, String> entry : mapSettings.entrySet()) {
                switch (entry.getKey()) {
                    case ("telegramBotName"):
                        telegramBotName = entry.getValue();
                        break;
                    case ("telegramBotToken"):
                        telegramBotToken = entry.getValue();
                        break;
                    case ("telegramBotServiceIP"):
                        telegramBotServiceIP = entry.getValue();
                        break;
                    case ("telegramBotServicePort"):
                        telegramBotServicePort = entry.getValue();
                        break;
                    default:
                        break;
                }
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
        }
    }
}
