package ru.konsist.JenkinsService.services;

import com.offbytwo.jenkins.JenkinsServer;
import ru.konsist.JenkinsService.models.JobJenkins;

import java.io.IOException;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        JenkinsService service = new JenkinsService();
        JenkinsServer server = service.getJenkinsServer("http://localhost:8080/", "serg", "serg");
        List<JobJenkins> jobs = service.getJenkinsJobs(server);
        JobJenkins jobJenkins = new JobJenkins();
        for (JobJenkins item:jobs) {
            System.out.println(item.toString());
            System.out.println(item.toJSON());
        }
    }
}
