package ru.konsist.models;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;
import ru.konsist.commandsTgBot.serviceCommand.HelpCommand;
import ru.konsist.commandsTgBot.serviceCommand.NonCommand;
import ru.konsist.commandsTgBot.serviceCommand.StartCommand;

@Component
@RequiredArgsConstructor
public final class WorkSupportsTgBot extends TelegramLongPollingCommandBot {
    @Autowired
    private ApplicationContext context;
    private String BOT_NAME;
    private String BOT_TOKEN;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void tgBotUpdateSettings() {
        this.BOT_NAME = SettingsTgBot.getInstance().getTelegramBotName();
        this.BOT_TOKEN = SettingsTgBot.getInstance().getTelegramBotToken();
    }
    public void tgBotAddCommand() {
        register(new StartCommand("start", "Старт"));
        register(new HelpCommand("help","Помощь"));
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
