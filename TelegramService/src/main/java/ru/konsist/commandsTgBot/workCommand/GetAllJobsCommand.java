package ru.konsist.commandsTgBot.workCommand;

import lombok.extern.java.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.JobJenkins;
import ru.konsist.supports.UtilsTgBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Log
public class GetAllJobsCommand extends WorkCommand {
    public GetAllJobsCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = UtilsTgBot.getUserName(user);
        String answer = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JobJenkins[] jobsJenkins = objectMapper.readValue(httpRequestJobs("http://localhost:6666/jobs/" + chat.getId()), JobJenkins[].class);
            for (JobJenkins item: jobsJenkins) {
                answer = item.toString();
                log.info(answer);
                sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, answer);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String httpRequestJobs(String urlString){
        try {
            final URL url = new URL(urlString);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(40000);
            con.setReadTimeout(40000);
            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                final StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            } catch (final Exception ex) {
                ex.printStackTrace();
                return "";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
