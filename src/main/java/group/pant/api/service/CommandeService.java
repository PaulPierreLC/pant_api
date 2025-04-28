package group.pant.api.service;

import group.pant.api.dto.CommandeDto;
import group.pant.api.model.*;
import group.pant.api.repository.CommandeRepository;
import group.pant.api.repository.AdresseRepository;
import group.pant.api.repository.PaiementRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final PaiementRepository paiementRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AdresseRepository adresseRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(int id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + id + " not found"));
    }

    public Commande addCommande(CommandeDto commandeDto) {
        Commande commande = new Commande();

        if (commandeDto.getIdPaiement() != null) {
            Paiement paiement = paiementRepository.findById(commandeDto.getIdPaiement())
                    .orElseThrow(() -> new RuntimeException("Paiement not found"));
            commande.setIdPaiement(paiement);
        }

        if (commandeDto.getIdUtilisateurClient() != null) {
            Utilisateur client = utilisateurRepository.findById(commandeDto.getIdUtilisateurClient())
                    .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
            commande.setIdUtilisateurClient(client);
        }

        if (commandeDto.getIdAdresse() != null) {
            Adresse adresse = adresseRepository.findById(commandeDto.getIdAdresse())
                    .orElseThrow(() -> new RuntimeException("Adresse not found"));
            commande.setIdAdresse(adresse);
        }

        if (commandeDto.getHeure() != null) {
            String heureStr = commandeDto.getHeure();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(heureStr, formatter);

            ZoneId zoneId = ZoneId.of("Europe/Paris");
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            LocalDate targetDate = now.toLocalTime().isAfter(localTime) ? now.toLocalDate().plusDays(1) : now.toLocalDate();
            ZonedDateTime heureZoned = ZonedDateTime.of(targetDate, localTime, zoneId);

            commande.setHeure(heureZoned.toInstant());
        }

        return commandeRepository.save(commande);
    }


    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }

    public Commande updateCommande(int id, Commande commande) {
        Commande existingCommande = commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + id + " not found"));

        existingCommande.setIdPaiement(commande.getIdPaiement());
        existingCommande.setIdUtilisateurClient(commande.getIdUtilisateurClient());
        existingCommande.setIdUtilisateurLivreur(commande.getIdUtilisateurLivreur());
        existingCommande.setIdAdresse(commande.getIdAdresse());
        existingCommande.setHeure(commande.getHeure());

        return commandeRepository.save(existingCommande);
    }

    public Commande patchCommande(int id, Map<String, Object> patch) {
        Commande existingCommande = commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idPaiement":
                    if (value instanceof Map<?, ?> paiementMap) {
                        Integer paiementId = (Integer) paiementMap.get("id");
                        Paiement paiement = paiementRepository.findById(paiementId)
                                .orElseThrow(() -> new EntityNotFoundException("Paiement with id " + paiementId + " not found"));
                        existingCommande.setIdPaiement(paiement);
                    }
                    break;
                case "idUtilisateurClient":
                    if (value instanceof Map<?, ?> utilisateurMap) {
                        Integer utilisateurClientId = (Integer) utilisateurMap.get("id");
                        Utilisateur utilisateurClient = utilisateurRepository.findById(utilisateurClientId)
                                .orElseThrow(() -> new EntityNotFoundException("Client with id " + utilisateurClientId + " not found"));
                        existingCommande.setIdUtilisateurClient(utilisateurClient);
                    }
                    break;
                case "idUtilisateurLivreur":
                    if (value instanceof Map<?, ?> utilisateurMap) {
                        Integer utilisateurLivreurId = (Integer) utilisateurMap.get("id");
                        Utilisateur utilisateurLivreur = utilisateurRepository.findById(utilisateurLivreurId)
                                .orElseThrow(() -> new EntityNotFoundException("Livreur with id " + utilisateurLivreurId + " not found"));
                        existingCommande.setIdUtilisateurLivreur(utilisateurLivreur);
                    }
                    break;
                case "idAdresse":
                    if (value instanceof Map<?, ?> adresseMap) {
                        Integer adresseId = (Integer) adresseMap.get("id");
                        Adresse adresse = adresseRepository.findById(adresseId)
                                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + adresseId + " not found"));
                        existingCommande.setIdAdresse(adresse);
                    }
                    break;
                case "heure":
                    existingCommande.setHeure((Instant) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });


        return commandeRepository.save(existingCommande);
    }
}
