package ru.konsist.JenkinsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Сервис для работы с Jenkins посредством Jenkins API
 */
@SpringBootApplication
public class JenkinsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JenkinsServiceApplication.class, args);
    }
}
