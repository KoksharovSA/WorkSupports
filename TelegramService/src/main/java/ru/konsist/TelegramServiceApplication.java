package ru.konsist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TelegramServiceApplication {
    private static ConfigurableApplicationContext ctx;
    public static void main(String[] args) {
        ctx = SpringApplication.run(TelegramServiceApplication.class, args);
    }
    public static void telegramServiceApplicationStop(){
        ctx.close();
    }
}