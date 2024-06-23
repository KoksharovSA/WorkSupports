package ru.konsist.commandsTgBot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konsist.commandsTgBot.workCommand.GetAllJobsCommand;
import ru.konsist.commandsTgBot.workCommand.GetSettingsCommand;
import ru.konsist.services.JobService;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;
import ru.konsist.commandsTgBot.serviceCommand.HelpCommand;
import ru.konsist.commandsTgBot.serviceCommand.NonCommand;
import ru.konsist.commandsTgBot.serviceCommand.StartCommand;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class WorkSupportsTgBot extends TelegramLongPollingCommandBot {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private JobService jobService;
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
        register(new HelpCommand("help", "Помощь"));
        register(new GetAllJobsCommand("jobs", "Получить все задачи"));
        register(new GetSettingsCommand("settings", "Получить настройки"));
    }


    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                try {
                    Message msg = update.getMessage();
                    Long chatId = msg.getChatId();
                    String userName = UtilsTgBot.getUserName(msg);
                    SendMessage answer = new SendMessage();
                    answer.setChatId(chatId.toString());
                    String textMessage = NonCommand.nonCommandExecute(chatId, userName, msg.getText());
                    answer.setText(textMessage);
                    execute(answer);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            try {
//                execute(new SendMessage().setText(update.getCallbackQuery().getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
                String[] textMessage = update.getCallbackQuery().getData().split(":");
                String result = "";
                switch (textMessage[0]){
                    case "build":
                        result = jobService.buildJob(update.getCallbackQuery().getFrom().getId().toString(), textMessage[1]);
                        break;
                }
                setAnswer(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getFrom().getUserName().toString(), result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Отправка ответа
     *
     * @param chatId   id чата
     * @param userName имя пользователя
     * @param text     текст ответа
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.enableMarkdown(true);
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
