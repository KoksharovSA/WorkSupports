package ru.konsist.commandsTgBot.workCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.SettingsUser;
import ru.konsist.services.JobService;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;

/**
 * Класс получения настроек пользователя подключения к сервису Jenkins
 */
@Log
public class GetSettingsCommand extends WorkCommand {
    @Autowired
    private JobService jobService;

    public GetSettingsCommand(String identifier, String description) {
        super(identifier, description);
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
                        textAnswer = jobService.httpRequest("http://"
                                + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                                + SettingsTgBot.getInstance().getJenkinsPort() + "/settings/" + chat.getId() + "/adduser/" + paramsSettings);
                        return;
                    default:
                        break;
                }
            }
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                SettingsUser settingsUser = objectMapper.readValue(jobService.httpRequest("http://"
                        + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                        + SettingsTgBot.getInstance().getJenkinsPort() + "/settings/" + chat.getId()), SettingsUser.class);
                textAnswer = settingsUser.toString().strip();
            } catch (JsonProcessingException e) {
                textAnswer = "❌Get settings ERROR";
                throw new RuntimeException(e);
            }
        }
        sendAnswerWithoutMarkdown(absSender, chat.getId(), this.getCommandIdentifier(), userName, textAnswer);
    }
}
