package ru.konsist.services;

import org.springframework.stereotype.Service;
import ru.konsist.supports.SettingsTgBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс сервиса для работы с задачами Jenkins сервиса
 */
@Service
public class JobService {
    /**
     * Метод запуска задачи на стороне Jenkins сервиса
     *
     * @param chatId ID чата
     * @param nameJob Название задачи
     * @return Строку статуса выполнения задачи
     */
    public String buildJob(String chatId, String nameJob){
        return httpRequest("http://" + SettingsTgBot.getInstance().getJenkinsHost() + ":" + SettingsTgBot.getInstance().getJenkinsPort() + "/jobs/" + chatId + "/" + nameJob + "/build");
    }

    /**
     * Метод для отправки HTTP GET запросов
     *
     * @param urlString URL запроса
     * @return Ответ на GET запрос
     */
    public String httpRequest(String urlString){
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
