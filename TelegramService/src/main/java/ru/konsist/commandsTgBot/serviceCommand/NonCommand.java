package ru.konsist.commandsTgBot.serviceCommand;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class NonCommand {
    public static String nonCommandExecute(Long chatId, String userName, String text) {
        log.info("Help answer: " + userName + "(" + chatId+ ")");
        return "Простите " + userName + "(" + chatId + "), я не понимаю Вас. Возможно, Вам поможет /help";
    }
}
