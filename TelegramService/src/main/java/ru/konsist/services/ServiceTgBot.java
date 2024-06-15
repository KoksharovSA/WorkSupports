package ru.konsist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.konsist.models.WorkSupportsTgBot;

import java.util.Map;

@Service
public class ServiceTgBot {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ConfigurableApplicationContext conContext;

    public void startTgBot(){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            WorkSupportsTgBot wsb = context.getBean(WorkSupportsTgBot.class);
            wsb.tgBotUpdateSettings();
            wsb.tgBotAddCommand();
            botsApi.registerBot(wsb);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void telegramServiceApplicationStop(){
        conContext.close();
    }
}
