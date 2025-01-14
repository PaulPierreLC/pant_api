package group.pant.api.service;

import group.pant.api.model.Action;
import group.pant.api.model.Log;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.LogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Log getLogById(int id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log with id " + id + " not found"));
    }

    public Log addLog(Log log) {
        return logRepository.save(log);
    }

    public void deleteLog(int id) {
        logRepository.deleteById(id);
    }

    public Log updateLog(int id, Log log) {
        Log existingLog = logRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log with id " + id + " not found"));

        existingLog.setIdAction(log.getIdAction());
        existingLog.setIdUtilisateur(log.getIdUtilisateur());
        existingLog.setParametre(log.getParametre());

        return logRepository.save(existingLog);
    }

    public Log patchLog(int id, Map<String, Object> patch) {
        Log existingLog = logRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "parametre":
                    existingLog.setParametre((String) value);
                    break;
                case "idAction":
                    existingLog.setIdAction((Action) value);
                    break;
                case "idUtilisateur":
                    existingLog.setIdUtilisateur((Utilisateur) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return logRepository.save(existingLog);
    }
}
