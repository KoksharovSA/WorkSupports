package ru.konsist.commandsTgBot.serviceCommand;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.supports.UtilsTgBot;

/**
 * Класс команды старта общения с ботом
 */
public class StartCommand extends ServiceCommand {
    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    /**
     * Метод выполнения команды старта
     *
     * @param absSender absSender to send messages over
     * @param user      the user who sent the command
     * @param chat      the chat, to be able to send replies
     * @param strings   passed arguments
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = UtilsTgBot.getUserName(user);
        sendAnswerWithMarkdown(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Давайте начнём! Если Вам нужна помощь, нажмите /help");
    }
}
