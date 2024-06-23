package ru.konsist.JenkinsService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.konsist.JenkinsService.models.SettingsUser;

/**
 * Класс репозитория настроек пользователя
 */
@Repository
public interface SettingsUserRepository extends JpaRepository<SettingsUser, Long> {
}
