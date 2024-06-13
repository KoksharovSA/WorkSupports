package ru.konsist.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.konsist.TelegramServiceApplication;
import ru.konsist.services.ServiceTgBot;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ControllerTgBot {
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
        TelegramServiceApplication.telegramServiceApplicationStop();
        return "TelegramServiceApplication stopped";
    }

}
