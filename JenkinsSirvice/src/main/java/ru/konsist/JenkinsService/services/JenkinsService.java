package ru.konsist.JenkinsService.services;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konsist.JenkinsService.models.JobJenkins;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JenkinsService {

    public JenkinsServer getJenkinsServer(String uri, String userName, String passwordOrToken) {
        try {
            return new JenkinsServer(new URI(uri), userName, passwordOrToken);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<JobJenkins> getJenkinsJobs(JenkinsServer jenkinsServer) {
        try {
            List<JobJenkins> jobJenkinsList = new ArrayList<>();
            for (Map.Entry item: jenkinsServer.getJobs().entrySet()) {
                jobJenkinsList.add((new JobJenkins().getJobDetails((Job)item.getValue())));
            }
            return jobJenkinsList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JobJenkins getJenkinsJobByName(JenkinsServer jenkinsServer, String nameJob) {
        try {
            return new JobJenkins().getJobDetails(jenkinsServer.getJobs().get(nameJob));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public QueueReference buildJenkinsJobByName(JenkinsServer jenkinsServer, String nameJob) {
        try {

            return jenkinsServer.getJob(nameJob).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
