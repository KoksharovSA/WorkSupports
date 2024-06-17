package ru.konsist.JenkinsService.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.konsist.JenkinsService.models.JobJenkins;
import ru.konsist.JenkinsService.models.SettingsUser;
import ru.konsist.JenkinsService.services.JenkinsService;
import ru.konsist.JenkinsService.services.SettingsUserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Log
public class RequestJenkinsController {
    private final SettingsUserService settingsUserService;
    private final JenkinsService jenkinsService;

    @GetMapping("/jobs/{chatId}")
    public List<JobJenkins> getAllJobs(@PathVariable Long chatId) {
        log.info("GetRequest(\"/jobs/\"" + chatId + ")");
        List<JobJenkins> result = new ArrayList<>();
        try{
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();
            result = jenkinsService.getJenkinsJobs(
                    jenkinsService.getJenkinsServer(
                            settingsUser.getUri(),
                            settingsUser.getLogin(),
                            settingsUser.getPassword()));
        } catch (Exception ex){
            log.info("ERROR getAllJobs");
            System.out.println(ex);
        }
        return result;
    }

    @GetMapping("/settings/{chatId}")
    public SettingsUser getSettingsUser(@PathVariable Long chatId) {
        log.info("GetRequest(\"/settings/\"" + chatId + ")");
        SettingsUser result = new SettingsUser();
        try{
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();
            result = settingsUser;
        } catch (Exception ex){
            log.info("ERROR getSettingsUser");
            System.out.println(ex);
        }
        return result;
    }
}
