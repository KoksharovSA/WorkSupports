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
        return "userName='" + userName + '\'' +
                "\nchatId='" + chatId + '\'' +
                "\nuri='" + uri + '\'' +
                "\nlogin='" + login + '\'' +
                "\npassword='" + password + '\'' +
                "\ndateCreate='" + dateCreate + '\'' +
                "\ndateUpdate='" + dateUpdate + '\'';
    }
}
