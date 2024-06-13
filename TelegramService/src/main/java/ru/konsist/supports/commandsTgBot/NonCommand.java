package ru.konsist.supports.commandsTgBot;

public class NonCommand {
    public static String nonCommandExecute(Long chatId, String userName, String text) {
        return "Простите " + userName + ", я не понимаю Вас. Возможно, Вам поможет /help";
    }
}
