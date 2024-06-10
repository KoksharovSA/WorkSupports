package ru.konsist.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.konsist.TelegramServiceApplication;
import ru.konsist.services.TestService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping
    @RequestMapping("/test")
    public void testResponse(@RequestBody Map request){
        testService.testMessage(request.toString());
    }

    @PostMapping
    @RequestMapping("/StopService")
    public void testStopService(@RequestBody Map request){
        TelegramServiceApplication.telegramServiceApplicationStop();
    }

}
