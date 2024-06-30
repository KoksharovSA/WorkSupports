package ru.konsist.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.konsist.models.JobJenkins;
import ru.konsist.models.SettingsUser;
import ru.konsist.supports.SettingsTgBot;
import ru.konsist.supports.UtilsTgBot;

/**
 * Класс сервиса для работы с задачами Jenkins сервиса
 */
@Service
public class ServiceForWorkWithJenkinsService {
    /**
     * Метод отправки HTTP запроса JenkinsServices для получения настроек пользователя
     *
     * @param chatId ID чата
     * @return строку результата создания настроек
     */
    public SettingsUser getSettingsUser(Long chatId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SettingsUser settingsUser = objectMapper.readValue(UtilsTgBot.httpRequest("http://"
                + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                + SettingsTgBot.getInstance().getJenkinsPort() + "/settings/" + chatId), SettingsUser.class);
        return settingsUser;
    }

    /**
     * Метод отправки HTTP запроса JenkinsServices для создания строки настроек нового пользователя
     *
     * @param chatId         ID чата
     * @param paramsSettings параметры настроек
     * @return строку результата создания настроек
     */
    public String createSettingsUser(Long chatId, String paramsSettings) {
        return UtilsTgBot.httpRequest("http://"
                + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                + SettingsTgBot.getInstance().getJenkinsPort() + "/settings/" + chatId + "/adduser/" + paramsSettings);
    }

    /**
     * Метод отправки HTTP запроса сервису Jenkins для получения задачи по её имени
     *
     * @param chatId  ID чата
     * @param nameJob Имя задачи
     * @return Задачу сервиса Jenkins
     * @throws JsonProcessingException
     */
    public JobJenkins getJobByName(Long chatId, String nameJob) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String httpRequest = UtilsTgBot.httpRequest("http://"
                + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                + SettingsTgBot.getInstance().getJenkinsPort() + "/job/" + chatId + "/" + nameJob);
        JobJenkins jobsJenkins = objectMapper.readValue(httpRequest, JobJenkins.class);
        return jobsJenkins;
    }

    /**
     * Метод отправки HTTP запроса сервису Jenkins для получения всех задач
     *
     * @param chatId ID чата
     * @return Массив задач сервиса Jenkins
     * @throws JsonProcessingException
     */
    public JobJenkins[] getAllJobs(Long chatId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JobJenkins[] jobsJenkins = objectMapper.readValue(UtilsTgBot.httpRequest("http://"
                + SettingsTgBot.getInstance().getJenkinsHost() + ":"
                + SettingsTgBot.getInstance().getJenkinsPort() + "/jobs/" + chatId), JobJenkins[].class);
        return jobsJenkins;
    }

    /**
     * Метод запуска задачи на стороне Jenkins сервиса
     *
     * @param chatId  ID чата
     * @param nameJob Название задачи
     * @return Строку статуса выполнения задачи
     */
    public String buildJob(String chatId, String nameJob) {
        return UtilsTgBot.httpRequest("http://" + SettingsTgBot.getInstance().getJenkinsHost() + ":" + SettingsTgBot.getInstance().getJenkinsPort() + "/jobs/" + chatId + "/" + nameJob + "/build");
    }
}
