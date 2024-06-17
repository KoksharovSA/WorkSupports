package ru.konsist.commandsTgBot.serviceCommand;

import org.springframework.stereotype.Component;

@Component
public class NonCommand {
    public static String nonCommandExecute(Long chatId, String userName, String text) {
        return "Простите " + userName + "(" + chatId + "), я не понимаю Вас. Возможно, Вам поможет /help";
    }
}
