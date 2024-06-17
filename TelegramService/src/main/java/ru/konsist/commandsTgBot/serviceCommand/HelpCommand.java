package ru.konsist.commandsTgBot.serviceCommand;

import lombok.extern.java.Log;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.supports.UtilsTgBot;
@Log
public class HelpCommand extends ServiceCommand{
    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        log.info("Help answer: " + user.getUserName() + "(" + chat.getId()+ ")");
        String userName = UtilsTgBot.getUserName(user);

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Я бот, который поможет Вам выполнять рабочие задачи DevOps инженера.\n\n" +
                        "❗*Список команд*\n" +
                        "/jobs - просмотреть текущие задачи\n" +
                        "/settings - просмотреть текущие настройки\n" +
                        "/help - помощь\n\n"
                        );
    }
}
