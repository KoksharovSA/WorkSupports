package ru.konsist.supports;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTgBotTest {

    @Test
    void getInstance() {
        assertEquals(SettingsTgBot.getInstance(), SettingsTgBot.getInstance());
    }

    @Test
    void getJenkinsHost() {
        assertEquals(SettingsTgBot.getInstance().getTelegramBotName(), "telegramBotName");
    }

    @Test
    void getJenkinsPort() {
        assertEquals(SettingsTgBot.getInstance().getTelegramBotToken(), "telegramBotToken");
    }

    @Test
    void getTelegramBotName() {
        assertEquals(SettingsTgBot.getInstance().getJenkinsHost(), "jenkinsHost");
    }

    @Test
    void getTelegramBotToken() {
        assertEquals(SettingsTgBot.getInstance().getJenkinsPort(), "jenkinsPort");
    }
}