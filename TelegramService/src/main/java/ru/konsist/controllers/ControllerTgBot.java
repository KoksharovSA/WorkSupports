package ru.konsist.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.konsist.services.ServiceForWorkWithTgBot;

/**
 * Класс контроллера Telegram бота
 */
@RestController
@RequiredArgsConstructor
public class ControllerTgBot {
    /**
     * Сервис Telegram бота
     */
    @Autowired
    private final ServiceForWorkWithTgBot serviceForWorkWithTgBot;

    /**
     * GET метод REST контроллера запускающий Telegram бота
     *
     * @return Строку результата
     */
    @GetMapping
    @RequestMapping("/service/startTgBot")
    public String startTgBotService(){
        try {
            serviceForWorkWithTgBot.startTgBot();
            return "Telegram bot started!";
        } catch (Exception ex){
            ex.printStackTrace();
            return "Error started telegram bot!";
        }
    }

    /**
     * GET метод REST контроллера для остановки приложения
     *
     * @return Строку результата
     */
    @GetMapping
    @RequestMapping("/service/StopService")
    public String stopTgBotApp(){
        serviceForWorkWithTgBot.telegramServiceApplicationStop();
        return "TelegramServiceApplication stopped";
    }

}
