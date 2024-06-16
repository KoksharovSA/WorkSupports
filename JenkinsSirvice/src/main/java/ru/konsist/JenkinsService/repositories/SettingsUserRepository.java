package ru.konsist.JenkinsService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konsist.JenkinsService.models.SettingsUser;

@Repository
public interface SettingsUserRepository extends JpaRepository<SettingsUser, Long> {
}
