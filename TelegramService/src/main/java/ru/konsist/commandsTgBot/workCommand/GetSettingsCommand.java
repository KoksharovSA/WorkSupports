package ru.konsist.commandsTgBot.workCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.SettingsUser;
import ru.konsist.services.ServiceForWorkWithJenkinsService;
import ru.konsist.supports.UtilsTgBot;


/**
 * Класс получения или создания настроек пользователя подключения к сервису Jenkins
 */
@Log
public class GetSettingsCommand extends WorkCommand {
    private ServiceForWorkWithJenkinsService serviceForWorkWithJenkinsService;
    public GetSettingsCommand(String identifier, String description) {
        super(identifier, description);
        serviceForWorkWithJenkinsService = new ServiceForWorkWithJenkinsService();
    }

    /**
     * Метод для получения настроек пользователя подключения к сервису Jenkins
     *
     * @param absSender absSender to send messages over
     * @param user      the user who sent the command
     * @param chat      the chat, to be able to send replies
     * @param strings   passed arguments
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        log.info("Settings answer: " + user.getUserName() + "(" + chat.getId() + ")");
        String userName = UtilsTgBot.getUserName(user);
        String textAnswer = "";
        if (strings.length != 0) {
            for (String item : strings) {
                log.info("Settings argument:" + item + "(" + chat.getId() + ")");
                switch (item) {
                    case "adduser":
                        String paramsSettings = strings[1];
                        textAnswer = serviceForWorkWithJenkinsService.createSettingsUser(chat.getId(), paramsSettings);
                        return;
                    default:
                        break;
                }
            }
        } else {
            try {
                SettingsUser settingsUser = serviceForWorkWithJenkinsService.getSettingsUser(chat.getId());
                textAnswer = settingsUser.toString().strip();
            } catch (JsonProcessingException e) {
                textAnswer = "❌Get settings ERROR";
                throw new RuntimeException(e);
            }
        }
        sendAnswerWithoutMarkdown(absSender, chat.getId(), this.getCommandIdentifier(), userName, textAnswer);
    }
}
