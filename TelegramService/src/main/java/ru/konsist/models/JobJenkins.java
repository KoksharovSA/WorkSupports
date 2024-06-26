package ru.konsist.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс задачи Jenkins сервиса для принятия задач посредством HTTP запросов
 */
@Component
@Data
@RequiredArgsConstructor
public class JobJenkins {
    private String name;
    private String displayName;
    private String dateJob;
    private String description;
    private String resultLastBuild;
    private String lastBuildTextConsole;

    @Override
    public String toString() {
        return  "Name: " + name +
                "\nDisplay name: " + displayName +
                "\nDescription: " + description +
                "\nDate last execute job: " + dateJob +
                "\nResult last execute: " + resultLastBuild +
                "\nLast execute text console: " + lastBuildTextConsole;
    }

    public String toJSON() {
        return "{" +
                "'name'='" + name + '\'' +
                ", 'displayName'='" + displayName + '\'' +
                ", 'dateJob'='" + dateJob + '\'' +
                ", 'description'='" + description + '\'' +
                ", 'resultLastBuild'='" + resultLastBuild + '\'' +
                ", 'lastBuildTextConsole'='" + lastBuildTextConsole + '\'' +
                '}';
    }
}
