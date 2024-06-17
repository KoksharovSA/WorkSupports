package ru.konsist.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return  "name='" + name + '\'' +
                "\ndisplayName='" + displayName + '\'' +
                "\ndateJob='" + dateJob + '\'' +
                "\ndescription='" + description + '\'' +
                "\nresultLastBuild='" + resultLastBuild + '\'' +
                "\nlastBuildTextConsole='" + lastBuildTextConsole + '\'';
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
