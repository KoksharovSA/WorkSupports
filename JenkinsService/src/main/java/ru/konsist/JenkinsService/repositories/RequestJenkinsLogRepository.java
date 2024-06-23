package ru.konsist.JenkinsService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konsist.JenkinsService.models.RequestJenkinsLog;

/**
 * Класс репозитория логов
 */
@Repository
public interface RequestJenkinsLogRepository extends JpaRepository<RequestJenkinsLog, Long> {
}
