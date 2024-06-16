package ru.konsist.JenkinsService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konsist.JenkinsService.models.RequestJenkinsLog;

@Repository
public interface RequestJenkinsLogRepository extends JpaRepository<RequestJenkinsLog, Long> {
}
