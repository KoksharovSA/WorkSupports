package ru.konsist.commandsTgBot.serviceCommand;

import lombok.extern.java.Log;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.supports.UtilsTgBot;

/**
 * Класс команды помощи
 */
@Log
public class HelpCommand extends ServiceCommand {
    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    /**
     * Метод выполнения команды помощи
     *
     * @param absSender absSender to send messages over
     * @param user      the user who sent the command
     * @param chat      the chat, to be able to send replies
     * @param strings   passed arguments
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        log.info("Help answer: " + user.getUserName() + "(" + chat.getId() + ")");
        String userName = UtilsTgBot.getUserName(user);
        String defaultTextAnswer = "Я бот, который поможет Вам выполнять рабочие задачи DevOps инженера.\n\n" +
                "❗*Список команд*\n" +
                "/jobs - просмотреть текущие задачи\n" +
                "/settings - просмотреть текущие настройки\n" +
                "/help - помощь\n\n";
        String textAnswer = "";
        if (strings.length != 0) {
            for (String item : strings) {
                log.info("Help argument:" + item + "(" + chat.getId() + ")");
                switch (item) {
                    case "adduser":
                        textAnswer = "Чтобы добавить нового пользователя наберите команду: " +
                                "\n/settings adduser <username>,<chatId>,<host>,<port>,<login>,<password> . " +
                                "\nЧтобы узнать свой UserName и ChatId наберите команду: /help uid .";
                        break;
                    case "uid":
                        textAnswer = "UserName: " + user.getUserName() +
                                "\nChatId: " + chat.getId();
                        break;
                    default:
                        textAnswer = defaultTextAnswer;
                        break;
                }
            }
        } else {
            textAnswer = defaultTextAnswer;
        }
        sendAnswerWithoutMarkdown(absSender, chat.getId(), this.getCommandIdentifier(), userName, textAnswer);
    }
}
