package ru.konsist.commandsTgBot.workCommand;

import lombok.extern.java.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.JobJenkins;
import ru.konsist.services.JobService;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Log
public class GetAllJobsCommand extends WorkCommand {
    private JobService jobService = new JobService();
    public GetAllJobsCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = UtilsTgBot.getUserName(user);
        String answer = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JobJenkins[] jobsJenkins = objectMapper.readValue(jobService.httpRequestJobs("http://"
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
