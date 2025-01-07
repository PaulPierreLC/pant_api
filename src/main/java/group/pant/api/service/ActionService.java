package group.pant.api.service;

import group.pant.api.model.Action;
import group.pant.api.repository.ActionRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    // Récupérer toutes les actions
    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    // Récupérer une action par son ID
    public Optional<Action> getActionById(Integer id) {
        return actionRepository.findById(id);
    }

    // Créer une nouvelle action
    public Action createAction(Action action) {
        return actionRepository.save(action);
    }

    // Mettre à jour une action existante
    public Action updateAction(Integer id, Action actionDetails) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));
        action.setNom(actionDetails.getNom());
        return actionRepository.save(action);
    }

    // Supprimer une action
    public String deleteAction(int id) {
        // Find and delete the action, then return a confirmation message
        actionRepository.deleteById(id);
        return "Action deleted successfully"; // Return a success message
    }

    // Mettre à jour partiellement une action
    public Action patchAction(Integer id, Map<String, Object> updates) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));

        updates.forEach((key, value) -> {
            if ("nom".equals(key)) {
                action.setNom((String) value);
            }
        });

        return actionRepository.save(action);
    }
}
