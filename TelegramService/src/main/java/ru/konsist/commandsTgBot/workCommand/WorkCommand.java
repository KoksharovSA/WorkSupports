package ru.konsist.commandsTgBot.workCommand;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс для рабочих команд
 */
abstract class WorkCommand extends BotCommand {
    WorkCommand(String identifier, String description) {
        super(identifier, description);
    }

    /**
     * Отправка ответа пользователю без markdown разметки
     *
     * @param absSender   AbsSender
     * @param chatId      ID чата
     * @param commandName Имя команды
     * @param userName    Имя пользователя
     * @param text        Текст ответа
     */
    void sendAnswerWithoutMarkdown(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(false);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправка ответа пользователю с markdown разметкой
     *
     * @param absSender   AbsSender
     * @param chatId      ID чата
     * @param commandName Имя команды
     * @param userName    Имя пользователя
     * @param text        Текст ответа
     */
    void sendAnswerWithMarkdown(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправка ответа пользователю с callback кнопками выполнения задачи
     *
     * @param absSender   AbsSender
     * @param chatId      ID чата
     * @param commandName Имя команды
     * @param userName    Имя пользователя
     * @param text        Текст ответа
     * @param job         Имя задачи
     */
    void sendAnswerWithButtonFromJob(AbsSender absSender, Long chatId, String commandName, String userName, String text, String job) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Build " + job);
        inlineKeyboardButton1.setCallbackData("build:" + job);
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage message = new SendMessage();
        message.setReplyMarkup(inlineKeyboardMarkup);
        message.enableMarkdown(false);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
