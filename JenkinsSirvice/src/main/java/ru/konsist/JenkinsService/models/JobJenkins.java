package ru.konsist.JenkinsService.models;

import com.offbytwo.jenkins.model.Job;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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

    public JobJenkins getJobDetails(Job job) {
        try {
            this.name = job.details().getName();
            this.displayName = job.details().getDisplayName();
            this.dateJob = new Date(new Timestamp(job.details().getLastBuild().details().getTimestamp()).getTime()).toString();
            this.description = job.details().getDescription();
            this.resultLastBuild = job.details().getLastBuild().details().getResult().name();
            this.lastBuildTextConsole = job.details().getLastBuild().details().getConsoleOutputText();
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
