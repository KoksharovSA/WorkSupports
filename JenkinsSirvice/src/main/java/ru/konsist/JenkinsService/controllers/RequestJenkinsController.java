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
@RequestMapping("/tasks")
@AllArgsConstructor
@Log
public class RequestJenkinsController {
    private final SettingsUserService settingsUserService;
    private final JenkinsService jenkinsService;

    @GetMapping("/{chatId}")
    public List<JobJenkins> getAllJobs(@PathVariable Long chatId) {
        List<JobJenkins> result = new ArrayList<>();
        try{
//            SettingsUser settingsTemp = new SettingsUser();
//            settingsTemp.setChatId("407040272");
//            settingsTemp.setUserName("serg_alex");
//            settingsTemp.setUri("http://localhost:8080/");
//            settingsTemp.setLogin("serg");
//            settingsTemp.setPassword("serg");
//            settingsUserService.createSettingsUser(settingsTemp);
            SettingsUser settingsUser = settingsUserService.getSettingsUserByChatId(chatId.toString()).get();

            result = jenkinsService.getJenkinsJobs(
                    jenkinsService.getJenkinsServer(
                            settingsUser.getUri(),
                            settingsUser.getLogin(),
                            settingsUser.getPassword()));
        } catch (Exception ex){
            System.out.println(ex);
        }
        return result;
    }
}
