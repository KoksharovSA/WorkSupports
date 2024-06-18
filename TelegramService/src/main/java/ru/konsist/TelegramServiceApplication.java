package ru.konsist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.konsist.services.ServiceTgBot;

@SpringBootApplication
public class TelegramServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TelegramServiceApplication.class, args);
        context.getBean(ServiceTgBot.class).startTgBot();
    }
}