package ru.konsist.JenkinsService.services;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

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

    public Map<String, Job> getJenkinsJobs(JenkinsServer jenkinsServer) {
        try {
            return jenkinsServer.getJobs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JobWithDetails getJobDetails(String jobName, JenkinsServer jenkinsServer) {
        try {
            return getJenkinsJobs(jenkinsServer).get(jobName).details();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
