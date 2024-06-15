package ru.konsist.supports.commandsTgBot.serviceCommand;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.konsist.supports.UtilsTgBot;

public class HelpCommand extends ServiceCommand{
    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = UtilsTgBot.getUserName(user);

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Я бот, который поможет Вам выполнять рабочие задачи DevOps инженера.\n\n" +
                        "❗*Список команд*\n" +
                        "/settings - просмотреть текущие настройки\n" +
                        "/help - помощь\n\n"
                        );
    }
}
