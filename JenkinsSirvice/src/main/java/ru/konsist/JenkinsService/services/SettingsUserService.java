package ru.konsist.JenkinsService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konsist.JenkinsService.models.SettingsUser;
import ru.konsist.JenkinsService.repositories.SettingsUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingsUserService {
    private final SettingsUserRepository settingsUserRepository;

    public List<SettingsUser> getAllSettingsUser(){
        return settingsUserRepository.findAll();
    }

    public Optional<SettingsUser> getSettingsUserById(long id){
        return settingsUserRepository.findById(id);
    }

    public List<SettingsUser> getSettingsUserByChatId(String chatId){
        return getAllSettingsUser().stream().toList().stream().filter(x->x.getChatId() == chatId).toList();
    }

    public SettingsUser createSettingsUser(SettingsUser settingsUser){
        return settingsUserRepository.save(settingsUser);
    }

    public SettingsUser updateSettingsUser(Long id, SettingsUser newSettingsUser){
        Optional<SettingsUser> optionalSettingsUser = settingsUserRepository.findById(id);
        if (optionalSettingsUser.isPresent()){
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

    public void deleteSettingsUser(Long id){
        settingsUserRepository.deleteById(id);
    }
}
