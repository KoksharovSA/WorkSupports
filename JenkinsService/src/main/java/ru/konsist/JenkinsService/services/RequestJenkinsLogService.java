package ru.konsist.JenkinsService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konsist.JenkinsService.models.RequestJenkinsLog;
import ru.konsist.JenkinsService.repositories.RequestJenkinsLogRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервиса работающего логами запросов в базе данных
 */
@Service
@RequiredArgsConstructor
public class RequestJenkinsLogService {
    private final RequestJenkinsLogRepository requestJenkinsLogRepository;

    /**
     * Метод получения всех логов из базы данных
     *
     * @return Список элементов класса лога
     */
    public List<RequestJenkinsLog> getAllRequestJenkinsLog() {
        return requestJenkinsLogRepository.findAll();
    }

    /**
     * Метод возвращающий элемент лога из базы данных по ID
     *
     * @param id ID лога
     * @return Класс лога
     */
    public Optional<RequestJenkinsLog> getRequestJenkinsLogById(long id) {
        return requestJenkinsLogRepository.findById(id);
    }


    /**
     * Метод возвращающий коллекцию элементов лога из базы данных по ID чата
     *
     * @param chatId ID чата
     * @return Коллекция элементов лога
     */
    public List<RequestJenkinsLog> geRequestJenkinsLogByChatId(String chatId) {
        return getAllRequestJenkinsLog().stream().toList().stream().filter(x -> x.getChatId() == chatId).toList();
    }

    /**
     * Метод создания записи лога в базе данных
     *
     * @param requestJenkinsLog Экземпляр лога
     * @return Экземпляр лога
     */
    public RequestJenkinsLog createRequestJenkinsLog(RequestJenkinsLog requestJenkinsLog) {
        return requestJenkinsLogRepository.save(requestJenkinsLog);
    }

    /**
     * Метод обновляющий элемент лога в базе данных
     *
     * @param id                   ID лога в базе данных
     * @param newRequestJenkinsLog Новый элемент лога
     * @return Новый элемент лога
     */
    public RequestJenkinsLog updateRequestJenkinsLog(Long id, RequestJenkinsLog newRequestJenkinsLog) {
        Optional<RequestJenkinsLog> optionalRequestJenkinsLog = requestJenkinsLogRepository.findById(id);
        if (optionalRequestJenkinsLog.isPresent()) {
            RequestJenkinsLog requestJenkinsLog = optionalRequestJenkinsLog.get();
            requestJenkinsLog.setUserName(newRequestJenkinsLog.getUserName());
            requestJenkinsLog.setRequest(newRequestJenkinsLog.getRequest());
            requestJenkinsLog.setResponse(newRequestJenkinsLog.getResponse());
            return requestJenkinsLogRepository.save(requestJenkinsLog);
        } else {
            throw new IllegalArgumentException("Settings not found with id: " + id);
        }
    }

    /**
     * Метод эдаляющий запись лога из базы данных по ID
     *
     * @param id ID лога в базе данных
     */
    public void deleteRequestJenkinsLog(Long id) {
        requestJenkinsLogRepository.deleteById(id);
    }
}
