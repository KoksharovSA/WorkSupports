package ru.konsist.commandsTgBot.workCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.models.SettingsUser;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Log
public class GetSettingsCommand extends WorkCommand {
    public GetSettingsCommand(String identifier, String description) {
        super(identifier, description);
    }

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
                        textAnswer = httpRequestSettings("http://"
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
                SettingsUser settingsUser = objectMapper.readValue(httpRequestSettings("http://"
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

    private String httpRequestSettings(String urlString){
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
