package ru.konsist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.konsist.models.WorkSupportsTgBot;
import ru.konsist.services.ServiceTgBot;

@SpringBootApplication
public class TelegramServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TelegramServiceApplication.class, args);
        context.getBean(ServiceTgBot.class).startTgBot();
    }
}