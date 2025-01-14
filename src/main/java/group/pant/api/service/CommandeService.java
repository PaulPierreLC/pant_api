package group.pant.api.service;

import group.pant.api.model.Commande;
import group.pant.api.model.Adresse;
import group.pant.api.model.Paiement;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.CommandeRepository;
import group.pant.api.repository.AdresseRepository;
import group.pant.api.repository.PaiementRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Commande addCommande(Commande commande) {
        commandeRepository.save(commande);
        return commande;
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
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });


        return commandeRepository.save(existingCommande);
    }
}
