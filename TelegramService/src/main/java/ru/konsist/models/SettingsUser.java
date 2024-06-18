package ru.konsist.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@RequiredArgsConstructor
public class SettingsUser {
    private Long Id;
    private String userName;
    private String chatId;
    private String uri;
    private String login;
    private String password;
    private String dateCreate;
    private String dateUpdate;

    @Override
    public String toString() {
        return "User name: " + userName +
                "\nChat Id: " + chatId +
                "\nUri: " + uri +
                "\nLogin: " + login +
                "\nPassword: " + password +
                "\nCreate date: " + dateCreate +
                "\nUpdate date: " + dateUpdate;
    }
}
