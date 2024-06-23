package ru.konsist.JenkinsService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konsist.JenkinsService.models.SettingsUser;
import ru.konsist.JenkinsService.repositories.SettingsUserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс настроек пользователя для подключения к сервису Jenkins
 */
@Service
@RequiredArgsConstructor
public class SettingsUserService {
    private final SettingsUserRepository settingsUserRepository;

    /**
     * Метод получения настроек всех пользователей из базы данных
     *
     * @return Коллекцию настроек пользователей
     */
    public List<SettingsUser> getAllSettingsUser() {
        return settingsUserRepository.findAll();
    }

    /**
     * Метод для получения настройки по ID настройки из базы данных
     *
     * @param id ID настройки
     * @return Настройку пользователя
     */
    public Optional<SettingsUser> getSettingsUserById(long id) {
        return settingsUserRepository.findById(id);
    }

    /**
     * Метод для получения настройки по ID чата из базы данных
     *
     * @param chatId ID Чата
     * @return Настройку пользователя
     */
    public Optional<SettingsUser> getSettingsUserByChatId(String chatId) {
        return getAllSettingsUser().stream().toList().stream().filter(x -> x.getChatId().equals(chatId)).findFirst();
    }

    /**
     * Метод добавляющий настройку в базу данных
     *
     * @param settingsUser Объект настройки пользователя
     * @return Объект настройки пользователя
     */
    public SettingsUser createSettingsUser(SettingsUser settingsUser) {
        return settingsUserRepository.save(settingsUser);
    }

    /**
     * Метод для обновления настройки пользователя в базе данных
     *
     * @param id              ID настройки в базе данных
     * @param newSettingsUser Объект настройки
     * @return Объект настройки
     */
    public SettingsUser updateSettingsUser(Long id, SettingsUser newSettingsUser) {
        Optional<SettingsUser> optionalSettingsUser = settingsUserRepository.findById(id);
        if (optionalSettingsUser.isPresent()) {
            SettingsUser settingsUser = optionalSettingsUser.get();
            settingsUser.setUserName(newSettingsUser.getUserName());
            settingsUser.setLogin(newSettingsUser.getLogin());
            settingsUser.setPassword(newSettingsUser.getPassword());
            settingsUser.setUri(newSettingsUser.getUri());
            return settingsUserRepository.save(settingsUser);
        } else {
            throw new IllegalArgumentException("Settings not found with id: " + id);
        }
    }

    /**
     * Метод удаления настройки из базы данных по ID
     *
     * @param id ID настройки в базе данных
     */
    public void deleteSettingsUser(Long id) {
        settingsUserRepository.deleteById(id);
    }
}
