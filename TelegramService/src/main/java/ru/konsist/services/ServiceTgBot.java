package ru.konsist.services;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.konsist.models.TgBot;

/**
 * Сервис работы с Telegram ботом
 */
@Service
@Log
public class ServiceTgBot {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ConfigurableApplicationContext conContext;

    /**
     * Метод для запуска Telegram бота
     */
    public void startTgBot(){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TgBot wsb = context.getBean(TgBot.class);
            wsb.tgBotUpdateSettings();
            wsb.tgBotAddCommand();
            botsApi.registerBot(wsb);
            log.info("Telegram bot started successfully");
        } catch (TelegramApiException e) {
            log.info("Telegram Bot started ERROR");
            e.printStackTrace();
        }
    }

    /**
     * Метод для остановки приложения
     */
    public void telegramServiceApplicationStop(){
        log.info("Telegram service application stopped");
        conContext.close();
    }
}
