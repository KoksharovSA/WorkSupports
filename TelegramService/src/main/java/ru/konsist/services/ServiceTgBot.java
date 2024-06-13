package ru.konsist.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.konsist.models.WorkSupportsTgBot;

import java.util.Map;

@Service
public class ServiceTgBot {
    private static final Map<String, String> getenv = System.getenv();
    public void startTgBot(){
        try {
            System.getProperties().put("proxySet", "true");

            System.getProperties().put("socksProxyHost", "127.0.0.1");

            System.getProperties().put("socksProxyPort", "9150");

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new WorkSupportsTgBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
