package group.pant.api.controller;

import group.pant.api.model.Plat;
import group.pant.api.model.Role;
import group.pant.api.model.Utilisateur;
import group.pant.api.model.Vehicule;
import group.pant.api.service.PlatService;
import group.pant.api.service.RoleService;
import group.pant.api.service.UtilisateurService;
import group.pant.api.service.VehiculeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ApiController {

    private final UtilisateurService utilisateurService;
    private final PlatService platService;
    private final RoleService roleService;
    private final VehiculeService vehiculeService;
    private final VehiculeTypeService VehiculeTypeService;
    private final VilleService villeService;
    private final StatutService statutService;
    private final ReservationService reservationService;

    // Injection par constructeur
    public ApiController(UtilisateurService utilisateurService, PlatService platService,
                         RoleService roleService, VehiculeService vehiculeService, VehiculeTypeService vehiculeTypeService, VilleService villeService, StatutService statutService,
                         ReservationService reservationService) {
        this.utilisateurService = utilisateurService;
        this.platService = platService;
        this.roleService = roleService;
        this.vehiculeService = vehiculeService;
        this.vehiculeTypeService = vehiculeTypeService;
        this.villeService = villeService;
        this.StatutService = statutService;
    }

    @GetMapping()
    public String accueil() {
        return "Bienvenue dans l'API de PANToplate";
    }

    // CRUD pour Utilisateurs (déjà existant)
    @GetMapping("utilisateurs")
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("utilisateurs/{id}")
    public Utilisateur getUtilisateur(@PathVariable int id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping("utilisateurs")
    public Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @DeleteMapping("utilisateurs/{id}")
    public String deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
        return "Utilisateur supprimé";
    }

    // CRUD pour Plats (déjà existant)
    @GetMapping("plats")
    public List<Plat> getPlats() {
        return platService.getAllPlats();
    }

    @GetMapping("plats/{id}")
    public Plat getPlat(@PathVariable int id) {
        return platService.getPlatById(id);
    }

    @PostMapping("plats")
    public Plat addPlat(@RequestBody Plat plat) {
        return platService.addPlat(plat);
    }

    @DeleteMapping("plats/{id}")
    public String deletePlat(@PathVariable int id) {
        return platService.deletePlat(id);
    }

    @PutMapping("plats/{id}")
    public ResponseEntity<Plat> updatePlat(@PathVariable int id, @RequestBody Plat plat) {
        Plat updatedPlat = platService.updatePlat(id, plat);
        return ResponseEntity.ok(updatedPlat);
    }

    @PatchMapping("plats/{id}")
    public ResponseEntity<Plat> patchPlat(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        Plat patchedPlat = platService.patchUser(id, patch);
        return ResponseEntity.ok(patchedPlat);
    }

    // CRUD pour Rôles (déjà existant)
    @GetMapping("roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("roles")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role roleDetails) {
        try {
            Role updatedRole = roleService.updateRole(id, roleDetails);
            return ResponseEntity.ok(updatedRole);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("roles/{id}")
    public ResponseEntity<Role> patchRole(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Role updatedRole = roleService.patchRole(id, updates);
            return ResponseEntity.ok(updatedRole);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CRUD pour Véhicules
    @GetMapping("vehicules")
    public List<Vehicule> getVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("vehicules/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Integer id) {
        return vehiculeService.getVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("vehicules")
    public Vehicule createVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.createVehicule(vehicule);
    }

    @PutMapping("vehicules/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Integer id, @RequestBody Vehicule vehiculeDetails) {
        try {
            Vehicule updatedVehicule = vehiculeService.updateVehicule(id, vehiculeDetails);
            return ResponseEntity.ok(updatedVehicule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("vehicules/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Integer id) {
        try {
            vehiculeService.deleteVehicule(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("vehicules/{id}")
    public ResponseEntity<Vehicule> patchVehicule(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Vehicule updatedVehicule = vehiculeService.patchVehicule(id, updates);
            return ResponseEntity.ok(updatedVehicule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //CRUD pour les types de véhicules

    // Récupérer tous les types de véhicules
    @GetMapping("vehicules/types")
    public List<VehiculeType> getAllVehiculeTypes() {
        return vehiculeTypeService.getAllVehiculeTypes();
    }

    // Récupérer un type de véhicule par ID
    @GetMapping("vehicules/types/{id}")
    public ResponseEntity<VehiculeType> getVehiculeTypeById(@PathVariable Integer id) {
        return vehiculeTypeService.getVehiculeTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un nouveau type de véhicule
    @PostMapping("vehicules/types")
    public VehiculeType createVehiculeType(@RequestBody VehiculeType vehiculeType) {
        return vehiculeTypeService.createVehiculeType(vehiculeType);
    }

    // Mettre à jour un type de véhicule
    @PutMapping("vehicules/types/{id}")
    public ResponseEntity<VehiculeType> updateVehiculeType(@PathVariable Integer id, @RequestBody VehiculeType vehiculeTypeDetails) {
        try {
            VehiculeType updatedVehiculeType = vehiculeTypeService.updateVehiculeType(id, vehiculeTypeDetails);
            return ResponseEntity.ok(updatedVehiculeType);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un type de véhicule
    @DeleteMapping("vehicules/types/{id}")
    public ResponseEntity<Void> deleteVehiculeType(@PathVariable Integer id) {
        try {
            vehiculeTypeService.deleteVehiculeType(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour partiellement un type de véhicule
    @PatchMapping("vehicules/types/{id}")
    public ResponseEntity<VehiculeType> patchVehiculeType(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            VehiculeType patchedVehiculeType = vehiculeTypeService.patchVehiculeType(id, updates);
            return ResponseEntity.ok(patchedVehiculeType);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CRUD pour les villes
    @GetMapping("villes")
    public List<Ville> getAllVilles() {
        return villeService.getAllVilles();
    }

    // Récupérer une ville par ID
    @GetMapping("villes/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable Integer id) {
        return villeService.getVilleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer une nouvelle ville
    @PostMapping("villes")
    public Ville createVille(@RequestBody Ville ville) {
        return villeService.createVille(ville);
    }

    // Mettre à jour une ville
    @PutMapping("villes/{id}")
    public ResponseEntity<Ville> updateVille(@PathVariable Integer id, @RequestBody Ville villeDetails) {
        try {
            Ville updatedVille = villeService.updateVille(id, villeDetails);
            return ResponseEntity.ok(updatedVille);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une ville
    @DeleteMapping("villes/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable Integer id) {
        try {
            villeService.deleteVille(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour partiellement une ville
    @PatchMapping("villes/{id}")
    public ResponseEntity<Ville> patchVille(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Ville patchedVille = villeService.patchVille(id, updates);
            return ResponseEntity.ok(patchedVille);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //CRUD pour les statuts

    // Récupérer tous les statuts
    @GetMapping("statuts")
    public List<Statut> getAllStatuts() {
        return statutService.getAllStatuts();
    }

    // Récupérer un statut par ID
    @GetMapping("statuts/{id}")
    public ResponseEntity<Statut> getStatutById(@PathVariable Integer id) {
        return statutService.getStatutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un nouveau statut
    @PostMapping("statuts")
    public Statut createStatut(@RequestBody Statut statut) {
        return statutService.createStatut(statut);
    }

    // Mettre à jour un statut
    @PutMapping("statuts/{id}")
    public ResponseEntity<Statut> updateStatut(@PathVariable Integer id, @RequestBody Statut statutDetails) {
        try {
            Statut updatedStatut = statutService.updateStatut(id, statutDetails);
            return ResponseEntity.ok(updatedStatut);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un statut
    @DeleteMapping("statuts/{id}")
    public ResponseEntity<Void> deleteStatut(@PathVariable Integer id) {
        try {
            statutService.deleteStatut(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour partiellement un statut
    @PatchMapping("statuts/{id}")
    public ResponseEntity<Statut> patchStatut(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Statut patchedStatut = statutService.patchStatut(id, updates);
            return ResponseEntity.ok(patchedStatut);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //CRUD pour reservations

    // Récupérer toutes les réservations
    @GetMapping("reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Récupérer une réservation par ID
    @GetMapping("reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer une nouvelle réservation
    @PostMapping("reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // Mettre à jour une réservation
    @PutMapping("reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody Reservation reservationDetails) {
        try {
            Reservation updatedReservation = reservationService.updateReservation(id, reservationDetails);
            return ResponseEntity.ok(updatedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une réservation
    @DeleteMapping("reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour partiellement une réservation
    @PatchMapping("reservations/{id}")
    public ResponseEntity<Reservation> patchReservation(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Reservation patchedReservation = reservationService.patchReservation(id, updates);
            return ResponseEntity.ok(patchedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

