package ru.konsist.JenkinsService.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Класс настроек пользователя для доступа к сервису Jenkins через JenkinsApi
 */
@Entity
@Table(name = "settings_users")
@Data
public class SettingsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 2000)
    private String userName;

    @Column(length = 2000)
    private String chatId;

    @Column(length = 2000)
    private String uri;

    @Column(length = 2000)
    private String login;

    @Column(length = 2000)
    private String password;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
}
