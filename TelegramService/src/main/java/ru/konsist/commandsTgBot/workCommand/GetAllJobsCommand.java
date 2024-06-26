package ru.konsist.commandsTgBot.workCommand;

import lombok.extern.java.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.JobJenkins;
import ru.konsist.services.JobService;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;

/**
 * Класс команды получения всех задач пользователя сервиса Jenkins
 */
@Log
public class GetAllJobsCommand extends WorkCommand {
    private JobService jobService = new JobService();
    public GetAllJobsCommand(String identifier, String description) {
        super(identifier, description);
    }

    /**
     * Метод получения всех задач пользователя сервиса Jenkins
     *
     * @param absSender absSender to send messages over
     * @param user      the user who sent the command
     * @param chat      the chat, to be able to send replies
     * @param strings   passed arguments
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = UtilsTgBot.getUserName(user);
        String answer = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JobJenkins[] jobsJenkins = objectMapper.readValue(jobService.httpRequest("http://"
                    + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                    + SettingsTgBot.getInstance().getJenkinsPort() + "/jobs/" + chat.getId()), JobJenkins[].class);
            for (JobJenkins item: jobsJenkins) {
                answer = item.toString();
                log.info(answer);
                sendAnswerWithButtonFromJob(absSender, chat.getId(), this.getCommandIdentifier(), userName, answer, item.getName());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
