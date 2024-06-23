package ru.konsist.commandsTgBot.serviceCommand;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

/**
 * Класс генерации ответов на сообщение не являющегося командой
 */
@Component
@Log
public class NonCommand {
    /**
     *Метод генерации ответа на сообщение не являющегося командой
     * @param chatId ID чата
     * @param userName Имя пользователя
     * @param text Входящий текст сообщения
     * @return Сообщение ответ
     */
    public static String nonCommandExecute(Long chatId, String userName, String text) {
        log.info("Help answer: " + userName + "(" + chatId+ ") " + text);
        return "Простите " + userName + "(" + chatId + "), я не понимаю Вас. Возможно, Вам поможет /help";
    }
}
