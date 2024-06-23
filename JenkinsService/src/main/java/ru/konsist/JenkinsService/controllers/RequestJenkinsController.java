package ru.konsist.JenkinsService.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.konsist.JenkinsService.models.JobJenkins;
import ru.konsist.JenkinsService.models.RequestJenkinsLog;
import ru.konsist.JenkinsService.models.SettingsUser;
import ru.konsist.JenkinsService.services.JenkinsService;
import ru.konsist.JenkinsService.services.RequestJenkinsLogService;
import ru.konsist.JenkinsService.services.SettingsUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс контроллера API сервиса Jenkins
 */
@RestController
@RequestMapping("/")
@AllArgsConstructor
@Log
public class RequestJenkinsController {
    private final SettingsUserService settingsUserService;
    private final JenkinsService jenkinsService;
    private final RequestJenkinsLogService jenkinsLogService;

    /**
     * HTTP GET метод получения всех активных задач
     *
     * @param chatId ID чата
     * @return Список задач сервиса Jenkins
     */
    @GetMapping("/jobs/{chatId}")
    public List<JobJenkins> getAllJobs(@PathVariable Long chatId) {
        log.info("GetRequest(/jobs/" + chatId + ")");
        List<JobJenkins> result = new ArrayList<>();
        try {
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();
            result = jenkinsService.getJenkinsJobs(
                    jenkinsService.getJenkinsServer(
                            settingsUser.getUri(),
                            settingsUser.getLogin(),
                            settingsUser.getPassword()));
        } catch (Exception ex) {
            log.info("ERROR getAllJobs");
            System.out.println(ex);
        }
        jenkinsLogService.createRequestJenkinsLog(new RequestJenkinsLog("Null", chatId.toString(), "GetRequest(/jobs/" + chatId + ")", result.toString()));
        return result;
    }

    /**
     * HTTP GET метод получения задачи по её имени
     *
     * @param chatId  ID чата
     * @param jobName Имя задачи
     * @return Задачу сервиса Jenkins по имени
     */
    @GetMapping("/jobs/{chatId}/{jobName}")
    public JobJenkins getJobByName(@PathVariable Long chatId, @PathVariable String jobName) {
        log.info("GetRequest(/jobs/" + chatId + "/" + jobName + ")");
        JobJenkins result = new JobJenkins();
        try {
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();
            result = jenkinsService.getJenkinsJobByName(
                    jenkinsService.getJenkinsServer(
                            settingsUser.getUri(),
                            settingsUser.getLogin(),
                            settingsUser.getPassword()), jobName);
        } catch (Exception ex) {
            log.info("ERROR getJobByName");
            System.out.println(ex);
        }
        jenkinsLogService.createRequestJenkinsLog(new RequestJenkinsLog("Null", chatId.toString(), "GetRequest(/jobs/" + chatId + "/" + jobName + ")", result.toString()));
        return result;
    }

    /**
     * HTTP GET метод запуска выполнения задачи по её имени
     *
     * @param chatId  ID чата
     * @param jobName Имя задачи
     * @return Сообщение о статусе выполнения запуска задачи
     */
    @GetMapping("/jobs/{chatId}/{jobName}/build")
    public String buildJobByName(@PathVariable Long chatId, @PathVariable String jobName) {
        log.info("GetRequest(/jobs/" + chatId + "/" + jobName + "/build)");
        String result = "";
        try {
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();
            jenkinsService.buildJenkinsJobByName(
                    jenkinsService.getJenkinsServer(
                            settingsUser.getUri(),
                            settingsUser.getLogin(),
                            settingsUser.getPassword()), jobName);
            result = "✅Building job " + jobName + " successfully.";
        } catch (Exception ex) {

            System.out.println(ex);
            result = "❌ERROR build job" + jobName;
        }
        jenkinsLogService.createRequestJenkinsLog(new RequestJenkinsLog("Null", chatId.toString(), "GetRequest(/jobs/" + chatId + "/" + jobName + "/build)", result.toString()));
        log.info(result);
        return result;
    }

    /**
     * HTTP GET метод получения настроек пользователя по ID чата
     *
     * @param chatId ID чата
     * @return Настройки пользователя
     */
    @GetMapping("/settings/{chatId}")
    public SettingsUser getSettingsUser(@PathVariable Long chatId) {
        log.info("GetRequest(/settings/" + chatId + ")");
        SettingsUser result = new SettingsUser();
        try {
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();
            result = settingsUser;
        } catch (Exception ex) {
            log.info("ERROR getSettingsUser");
            System.out.println(ex);
        }
        log.info(result.toString());
        jenkinsLogService.createRequestJenkinsLog(new RequestJenkinsLog("Null", chatId.toString(), "GetRequest(/settings/" + chatId + ")", result.toString()));
        return result;
    }

    /**
     * @param params Настройки пользователя в формате csv
     *               <username telegram>,
     *               <chatId telegram>,
     *               <host jenkins service>,
     *               <port jenkins service>,
     *               <login jenkins service>,
     *               <password jenkins service>
     * @return Отчёт о создании новых настроек пользователя
     */
    @GetMapping("/settings/{chatId}/adduser/{params}")
    public String addSettingsUser(@PathVariable Long chatId, @PathVariable String params) {
        log.info("GetRequest(/settings/" + chatId + "/adduser/" + params + ")");
        String result = "❌ERROR Added new user settings.";
        String[] paramsSettingsUser = params.split(",");
        SettingsUser newSettingsUser = new SettingsUser();
        if (paramsSettingsUser.length == 6) {
            newSettingsUser.setUserName(paramsSettingsUser[0]);
            newSettingsUser.setChatId(paramsSettingsUser[1]);
            newSettingsUser.setUri("http://" + paramsSettingsUser[2] + ":" + paramsSettingsUser[3] + "/");
            newSettingsUser.setLogin(paramsSettingsUser[4]);
            newSettingsUser.setPassword(paramsSettingsUser[5]);
            try {
                settingsUserService.createSettingsUser(newSettingsUser);
                result = "✅Added new user settings successfully.";
            } catch (Exception ex) {
                log.info("ERROR Added new user settings.");
                System.out.println(ex);
                result = "❌ERROR Added new user settings.";
            }
        } else {
            log.info("ERROR Incorrect number of parameters.");
            result = "❌ERROR Incorrect number of parameters.";
        }log.info(result);
        jenkinsLogService.createRequestJenkinsLog(new RequestJenkinsLog("Null", chatId.toString(), "GetRequest(/settings/" + chatId + "/adduser/" + params + ")", result));
        return result;
    }
}
