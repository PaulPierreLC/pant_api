package group.pant.api.service;

import group.pant.api.model.Action;
import group.pant.api.repository.ActionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public Action getActionById(Integer id) {
        return actionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Action with id " + id + " not found"));
    }

    public Action addAction(Action action) {
        return actionRepository.save(action);
    }

    public void deleteAction(int id) {
        actionRepository.deleteById(id);
    }

    public Action updateAction(Integer id, Action action) {
        Action existingAction = actionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Action with id " + id + " not found"));

        existingAction.setNom(action.getNom());

        return actionRepository.save(existingAction);
    }

    public Action patchAction(Integer id, Map<String, Object> patch) {
        Action existingAction = actionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Action with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingAction.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return actionRepository.save(existingAction);
    }
}
