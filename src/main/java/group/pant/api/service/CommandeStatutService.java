package group.pant.api.service;

import group.pant.api.dto.CommandeStatutDto;
import group.pant.api.model.*;
import group.pant.api.repository.CommandeStatutRepository;
import group.pant.api.repository.CommandeRepository;
import group.pant.api.repository.StatutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommandeStatutService {
    private final CommandeStatutRepository commandeStatutRepository;
    private final CommandeRepository commandeRepository;
    private final StatutRepository statutRepository;

    public List<CommandeStatut> getAllCommandeStatuts() {
        return commandeStatutRepository.findAll();
    }

    public CommandeStatut getCommandeStatutById(int id) {
        return commandeStatutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CommandeStatut with id " + id + " not found"));
    }

    public List<CommandeStatut> getCommandeStatutByCommandeId(int commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + commandeId + " not found"));

        return commandeStatutRepository.findByIdCommande(commande);
    }

    public CommandeStatut addCommandeStatut(CommandeStatutDto commandeStatutDto) {
        CommandeStatut commandeStatut = new CommandeStatut();

        Commande commande = commandeRepository.findById(commandeStatutDto.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        commandeStatut.setIdCommande(commande);

        Statut statut = statutRepository.findById(commandeStatutDto.getIdStatut())
                .orElseThrow(() -> new RuntimeException("Statut not found"));
        commandeStatut.setIdStatut(statut);

        return commandeStatutRepository.save(commandeStatut);
    }

    public void deleteCommandeStatut(int id) {
        commandeStatutRepository.deleteById(id);
    }

    public CommandeStatut updateCommandeStatut(int id, CommandeStatut commandeStatut) {
        CommandeStatut existingCommandeStatut = commandeStatutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CommandeStatut with id " + id + " not found"));

        existingCommandeStatut.setIdCommande(commandeStatut.getIdCommande());
        existingCommandeStatut.setIdStatut(commandeStatut.getIdStatut());
        existingCommandeStatut.setCommentaire(commandeStatut.getCommentaire());
        existingCommandeStatut.setLongitude(commandeStatut.getLongitude());
        existingCommandeStatut.setLattitude(commandeStatut.getLattitude());

        return commandeStatutRepository.save(existingCommandeStatut);
    }

    public CommandeStatut patchCommandeStatut(int id, Map<String, Object> patch) {
        CommandeStatut existingCommandeStatut = commandeStatutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CommandeStatut with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idCommande":
                    if (value instanceof Map<?, ?> commandeMap) {
                        Integer commandeId = (Integer) commandeMap.get("id");
                        Commande commande = commandeRepository.findById(commandeId)
                                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + commandeId + " not found"));
                        existingCommandeStatut.setIdCommande(commande);
                    }
                    break;
                case "idStatut":
                    if (value instanceof Map<?, ?> statutMap) {
                        Integer statutId = (Integer) statutMap.get("id");
                        Statut statut = statutRepository.findById(statutId)
                                .orElseThrow(() -> new EntityNotFoundException("Statut with id " + statutId + " not found"));
                        existingCommandeStatut.setIdStatut(statut);
                    }
                    break;
                case "commentaire":
                    existingCommandeStatut.setCommentaire((String) value);
                    break;
                case "longitude":
                    existingCommandeStatut.setLongitude((Double) value);
                    break;
                case "lattitude":
                    existingCommandeStatut.setLattitude((Double) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return commandeStatutRepository.save(existingCommandeStatut);
    }
}
