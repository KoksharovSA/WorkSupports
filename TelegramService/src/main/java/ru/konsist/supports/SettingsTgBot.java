package ru.konsist.supports;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class SettingsTgBot {
    private static SettingsTgBot settingsTgBotApp;

    private String telegramBotName;
    private String telegramBotToken;

    public String getTelegramBotName() {
        return telegramBotName;
    }

    public String getTelegramBotToken() {
        return telegramBotToken;
    }


    public static SettingsTgBot getInstance(){
        if (settingsTgBotApp == null) {
            settingsTgBotApp = new SettingsTgBot();
        }
        return settingsTgBotApp;
    }

    private SettingsTgBot() {
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
                    default:
                        break;
                }
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
        }
    }
}
