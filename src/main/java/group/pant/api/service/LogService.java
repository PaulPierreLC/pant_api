package group.pant.api.service;

import group.pant.api.model.Log;
import group.pant.api.repository.LogRepository;
import group.pant.api.model.Action;
import group.pant.api.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    // Méthode pour récupérer tous les logs
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Log getLogById(int id) {
        return logRepository.findById(id).orElse(null); // Ou gérer l'absence de log différemment.
    }

    // Méthode pour enregistrer un nouveau log
    public Log addLog(Action action, Utilisateur utilisateur, String parametre) {
        Log log = new Log();
        log.setIdAction(action);
        log.setIdUtilisateur(utilisateur);
        log.setParametre(parametre);
        log.setDateCreation(java.time.Instant.now()); // ou tu peux utiliser le @ColumnDefault (current_timestamp) dans ta base de données

        return logRepository.save(log);
    }

    // Méthode pour supprimer un log par son ID
    public String deleteLog(int id) {
        logRepository.deleteById(id);
        return "Deleted Log with id " + id;
    }

    // Méthode pour mettre à jour un log existant
    public Log updateLog(int id, Log logDetails) {
        Log existingLog = logRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));

        // Mettre à jour les propriétés du log
        existingLog.setIdAction(logDetails.getIdAction());
        existingLog.setIdUtilisateur(logDetails.getIdUtilisateur());
        existingLog.setParametre(logDetails.getParametre());
        existingLog.setDateCreation(logDetails.getDateCreation());

        return logRepository.save(existingLog);
    }

    // Méthode pour appliquer un patch à un log (mettre à jour certains champs)
    public Log patchLog(int id, Map<String, Object> patch) {
        Log existingLog = logRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));

        patch.forEach((key, value) -> {
            if (key.equals("parametre")) {
                existingLog.setParametre((String) value);
            } else if (key.equals("date_creation")) {
                existingLog.setDateCreation((java.time.Instant) value);
            } else {
                throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return logRepository.save(existingLog);
    }
}
