package ru.konsist.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.konsist.TelegramServiceApplication;
import ru.konsist.services.ServiceTgBot;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ControllerTgBot {
    @Autowired
    private final ServiceTgBot serviceTgBot;

    @GetMapping
    @RequestMapping("/service/startTgBot")
    public String testResponse(){
        serviceTgBot.startTgBot();
        return "Telegram bot started!";
    }

    @GetMapping
    @RequestMapping("/StopService")
    public String testStopService(@RequestBody Map request){
        serviceTgBot.telegramServiceApplicationStop();
        return "TelegramServiceApplication stopped";
    }

}
