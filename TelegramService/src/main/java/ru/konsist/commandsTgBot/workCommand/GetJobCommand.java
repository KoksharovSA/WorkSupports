package ru.konsist.commandsTgBot.workCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.JobJenkins;
import ru.konsist.services.ServiceForWorkWithJenkinsService;
import ru.konsist.supports.UtilsTgBot;

@Log
public class GetJobCommand extends WorkCommand {
    private ServiceForWorkWithJenkinsService serviceForWorkWithJenkinsService;

    public GetJobCommand(String identifier, String description) {
        super(identifier, description);
        serviceForWorkWithJenkinsService = new ServiceForWorkWithJenkinsService();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = UtilsTgBot.getUserName(user);
        String answer = "";
        try {
            if (strings.length > 0) {
                JobJenkins jobJenkins = serviceForWorkWithJenkinsService.getJobByName(chat.getId(), strings[0]);
                if (strings[0].equals(jobJenkins.getName())) {
                    answer = jobJenkins.toString();
                    log.info(answer);
                    sendAnswerWithButtonFromJob(absSender, chat.getId(), this.getCommandIdentifier(), userName, answer, jobJenkins.getName());
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
