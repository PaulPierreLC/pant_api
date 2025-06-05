package group.pant.api.service;

import group.pant.api.dto.CommandeDetailDto;
import group.pant.api.model.CommandeDetail;
import group.pant.api.model.Commande;
import group.pant.api.model.Plat;
import group.pant.api.repository.CommandeDetailRepository;
import group.pant.api.repository.CommandeRepository;
import group.pant.api.repository.PlatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommandeDetailService {
    private final CommandeDetailRepository commandeDetailRepository;
    private final CommandeRepository commandeRepository;
    private final PlatRepository platRepository;

    public List<CommandeDetail> getAllCommandeDetails() {
        return commandeDetailRepository.findAll();
    }

    public CommandeDetail getCommandeDetailById(int id) {
        return commandeDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CommandeDetail with id " + id + " not found"));
    }

    public List<CommandeDetail> getCommandeDetailsByCommandeId(Integer commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + commandeId + " not found"));

        return commandeDetailRepository.findByIdCommande(commande);
    }

    public CommandeDetail addCommandeDetail(CommandeDetailDto commandeDetailDto) {
        CommandeDetail commandeDetail = new CommandeDetail();

        Commande commande = commandeRepository.findById(commandeDetailDto.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        commandeDetail.setIdCommande(commande);

        Plat plat = platRepository.findById(commandeDetailDto.getIdPlat())
                .orElseThrow(() -> new RuntimeException("Plat not found"));
        commandeDetail.setIdPlat(plat);

        commandeDetail.setQuantite(commandeDetailDto.getQuantite());

        return commandeDetailRepository.save(commandeDetail);
    }

    public void deleteCommandeDetail(int id) {
        commandeDetailRepository.deleteById(id);
    }

    public CommandeDetail updateCommandeDetail(int id, CommandeDetail commandeDetail) {
        CommandeDetail existingCommandeDetail = commandeDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("commandeDetail with id " + id + " not found"));

        existingCommandeDetail.setIdCommande(commandeDetail.getIdCommande());
        existingCommandeDetail.setIdPlat(commandeDetail.getIdPlat());
        existingCommandeDetail.setQuantite(commandeDetail.getQuantite());

        return commandeDetailRepository.save(existingCommandeDetail);
    }

    public CommandeDetail patchCommandeDetail(int id, Map<String, Object> patch) {
        CommandeDetail existingCommandeDetail = commandeDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("commandeDetail with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idCommande":
                    if (value instanceof Map<?, ?> commandeMap) {
                        Integer commandeId = (Integer) commandeMap.get("id");
                        Commande commande = commandeRepository.findById(commandeId)
                                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + commandeId + " not found"));
                        existingCommandeDetail.setIdCommande(commande);
                    }
                    break;
                case "idPlat":
                    if (value instanceof Map<?, ?> platMap) {
                        Integer platId = (Integer) platMap.get("id");
                        Plat plat = platRepository.findById(platId)
                                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + platId + " not found"));
                        existingCommandeDetail.setIdPlat(plat);
                    }
                    break;
                case "quantite":
                    existingCommandeDetail.setQuantite((Integer) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });


        return commandeDetailRepository.save(existingCommandeDetail);
    }
}
