package ru.konsist.JenkinsService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konsist.JenkinsService.models.RequestJenkinsLog;
import ru.konsist.JenkinsService.repositories.RequestJenkinsLogRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestJenkinsLogService {
    private final RequestJenkinsLogRepository requestJenkinsLogRepository;

    public List<RequestJenkinsLog> getAllRequestJenkinsLog() {
        return requestJenkinsLogRepository.findAll();
    }

    public Optional<RequestJenkinsLog> getRequestJenkinsLogById(long id) {
        return requestJenkinsLogRepository.findById(id);
    }

    public List<RequestJenkinsLog> geRequestJenkinsLogByChatId(String chatId) {
        return getAllRequestJenkinsLog().stream().toList().stream().filter(x -> x.getChatId() == chatId).toList();
    }

    public RequestJenkinsLog createRequestJenkinsLog(RequestJenkinsLog requestJenkinsLog) {
        return requestJenkinsLogRepository.save(requestJenkinsLog);
    }

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

    public void deleteRequestJenkinsLog(Long id) {
        requestJenkinsLogRepository.deleteById(id);
    }
}
