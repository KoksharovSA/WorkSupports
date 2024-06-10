package ru.konsist.models;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.konsist.supports.Settings;

import java.util.Map;

public final class TelegramBot extends TelegramLongPollingCommandBot {

    @Override
    public String getBotUsername() {
        return Settings.getInstance().getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return Settings.getInstance().getTelegramBotToken();
    }

    @Override
    public void processNonCommandUpdate(Update update) {
    }

}
