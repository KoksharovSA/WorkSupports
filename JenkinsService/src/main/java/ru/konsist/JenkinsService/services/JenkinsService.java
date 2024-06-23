package ru.konsist.JenkinsService.services;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konsist.JenkinsService.models.JobJenkins;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс сервиса для работы с Jenkins API
 */
@Service
@RequiredArgsConstructor
public class JenkinsService {

    /**
     * Метод создания подключения к Jenkins серверу
     *
     * @param uri             URI Jenkins сервера
     * @param userName        Имя пользователя
     * @param passwordOrToken Пароль
     * @return Экземпляр класса подключения к серверу Jenkins
     */
    public JenkinsServer getJenkinsServer(String uri, String userName, String passwordOrToken) {
        try {
            return new JenkinsServer(new URI(uri), userName, passwordOrToken);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для получения коллекции задач через Jenkins API
     *
     * @param jenkinsServer Экземпляр класса подключения к серверу Jenkins
     * @return Коллекция задач Jenkins
     */
    public List<JobJenkins> getJenkinsJobs(JenkinsServer jenkinsServer) {
        try {
            List<JobJenkins> jobJenkinsList = new ArrayList<>();
            for (Map.Entry item : jenkinsServer.getJobs().entrySet()) {
                jobJenkinsList.add((new JobJenkins().getJobDetails((Job) item.getValue())));
            }
            return jobJenkinsList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для получения задачи по её имени через Jenkins API
     *
     * @param jenkinsServer Экземпляр класса подключения к серверу Jenkins
     * @param nameJob       Имя задачи
     * @return Задачу Jenkins
     */
    public JobJenkins getJenkinsJobByName(JenkinsServer jenkinsServer, String nameJob) {
        try {
            return new JobJenkins().getJobDetails(jenkinsServer.getJobs().get(nameJob));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Запускает выбранную по имени задачу
     *
     * @param jenkinsServer Экземпляр класса подключения к серверу Jenkins
     * @param nameJob       Имя задачи
     * @return Класс очереди выполнения задач
     */
    public QueueReference buildJenkinsJobByName(JenkinsServer jenkinsServer, String nameJob) {
        try {
            return jenkinsServer.getJob(nameJob).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
