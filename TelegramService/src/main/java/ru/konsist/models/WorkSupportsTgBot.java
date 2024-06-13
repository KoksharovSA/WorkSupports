package ru.konsist.models;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;
import ru.konsist.supports.commandsTgBot.NonCommand;
import ru.konsist.supports.commandsTgBot.StartCommand;

public final class WorkSupportsTgBot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public WorkSupportsTgBot() {
        super();
        this.BOT_NAME = SettingsTgBot.getInstance().getTelegramBotName();
        this.BOT_TOKEN = SettingsTgBot.getInstance().getTelegramBotToken();

        register(new StartCommand("start", "Старт"));
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = UtilsTgBot.getUserName(msg);

        String answer = NonCommand.nonCommandExecute(chatId, userName, msg.getText());
        setAnswer(chatId, userName, answer);
    }

    /**
     * Отправка ответа
     * @param chatId id чата
     * @param userName имя пользователя
     * @param text текст ответа
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
