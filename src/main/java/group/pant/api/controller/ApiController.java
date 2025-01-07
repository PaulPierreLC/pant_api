package group.pant.api.controller;

import group.pant.api.model.Cuisine;
import group.pant.api.model.Plat;
import group.pant.api.model.Restaurant;
import group.pant.api.model.Utilisateur;
import group.pant.api.model.Action;
import group.pant.api.model.Adresse;
import group.pant.api.model.Avis;
import group.pant.api.model.Reservation;
import group.pant.api.model.Role;
import group.pant.api.model.Statut;
import group.pant.api.model.Vehicule;
import group.pant.api.model.VehiculeType;
import group.pant.api.service.ActionService;
import group.pant.api.service.AdresseService;
import group.pant.api.service.AvisService;
import group.pant.api.service.ReservationService;
import group.pant.api.service.RoleService;
import group.pant.api.service.StatutService;
import group.pant.api.service.VehiculeService;
import group.pant.api.service.VehiculeTypeService;
import group.pant.api.service.CuisineService;
import group.pant.api.service.PlatService;
import group.pant.api.service.RestaurantService;
import group.pant.api.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ApiController {
    private final UtilisateurService utilisateurService;
    private final PlatService platService;
    private final RestaurantService restaurantService;
    private final CuisineService cuisineService;
    private final ActionService actionService;
    private final AdresseService adresseService;
    private final AvisService avisService;
    private final ReservationService reservationService;
    private final RoleService roleService;
    private final StatutService statutService;
    private final VehiculeService vehiculeService;
    private final VehiculeTypeService vehiculeTypeService;
    

    @GetMapping()
    public String accueil() {
        return "Bienvenue dans l'API de PANToplate";
    }

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

    @DeleteMapping("utilisateur/{id}")
    public String deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
        return "Utilisateur deleted";
    }

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
        Plat patchedPlat = platService.patchPlat(id, patch);
        return ResponseEntity.ok(patchedPlat);
    }

    @GetMapping("restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping("restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("restaurants/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        return restaurantService.deleteRestaurant(id);
    }

    @PutMapping("restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @PatchMapping("restaurants/{id}")
    public ResponseEntity<Restaurant> patchRestaurant(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        Restaurant patchedRestaurant = restaurantService.patchRestaurant(id, patch);
        return ResponseEntity.ok(patchedRestaurant);
    }

    @GetMapping("cuisines")
    public List<Cuisine> getCuisines() {
        return cuisineService.getAllCuisines();
    }

    @GetMapping("cuisines/{id}")
    public Cuisine getCuisine(@PathVariable int id) {
        return cuisineService.getCuisineById(id);
    }

    @PostMapping("cuisines")
    public Cuisine addCuisine(@RequestBody Cuisine cuisine) {
        return cuisineService.addCuisine(cuisine);
    }

    @DeleteMapping("cuisines/{id}")
    public String deleteCuisine(@PathVariable int id) {
        return cuisineService.deleteCuisine(id);
    }

    @PutMapping("cuisines/{id}")
    public ResponseEntity<Cuisine> updateCuisine(@PathVariable int id, @RequestBody Cuisine cuisine) {
        Cuisine updatedCuisine = cuisineService.updateCuisine(id, cuisine);
        return ResponseEntity.ok(updatedCuisine);
    }

    @PatchMapping("cuisines/{id}")
    public ResponseEntity<Cuisine> patchCuisine(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        Cuisine patchedCuisine = cuisineService.patchCuisine(id, patch);
        return ResponseEntity.ok(patchedCuisine);
    }

    //Action

        @GetMapping("actions")
    public List<Action> getActions() {
        return actionService.getAllActions();
    }

    @GetMapping("actions/{id}")
    public Action getAction(@PathVariable int id) {
        return actionService.getActionById(id);
    }

    @PostMapping("actions")
    public Action addAction(@RequestBody Action action) {
        return actionService.addAction(action);
    }

    @PutMapping("actions/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable int id, @RequestBody Action action) {
        Action updatedAction = actionService.updateAction(id, action);
        return ResponseEntity.ok(updatedAction);
    }

    @DeleteMapping("actions/{id}")
    public String deleteAction(@PathVariable int id) {
        return actionService.deleteAction(id);
    }

    // Adresse

    @GetMapping("adresses")
    public List<Adresse> getAdresses() {
        return adresseService.getAllAdresses();
    }

    @GetMapping("adresses/{id}")
    public Adresse getAdresse(@PathVariable int id) {
        return adresseService.getAdresseById(id);
    }

    @PostMapping("adresses")
    public Adresse addAdresse(@RequestBody Adresse adresse) {
        return adresseService.addAdresse(adresse);
    }

    @PutMapping("adresses/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable int id, @RequestBody Adresse adresse) {
        Adresse updatedAdresse = adresseService.updateAdresse(id, adresse);
        return ResponseEntity.ok(updatedAdresse);
    }

    @DeleteMapping("adresses/{id}")
    public String deleteAdresse(@PathVariable int id) {
        return adresseService.deleteAdresse(id);
    }

    @GetMapping("avis")
public List<Avis> getAvis() {
    return avisService.getAllAvis();
}

@GetMapping("avis/{id}")
public Avis getAvis(@PathVariable int id) {
    return avisService.getAvisById(id);
}

@PostMapping("avis")
public Avis addAvis(@RequestBody Avis avis) {
    return avisService.addAvis(avis);
}

@PutMapping("avis/{id}")
public ResponseEntity<Avis> updateAvis(@PathVariable int id, @RequestBody Avis avis) {
    Avis updatedAvis = avisService.updateAvis(id, avis);
    return ResponseEntity.ok(updatedAvis);
}

@DeleteMapping("avis/{id}")
public String deleteAvis(@PathVariable int id) {
    return avisService.deleteAvis(id);
}

// Reservation

@GetMapping("reservations")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("reservations/{id}")
    public Reservation getReservation(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping("reservations")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PutMapping("reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("reservations/{id}")
    public String deleteReservation(@PathVariable int id) {
        return reservationService.deleteReservation(id);
    }

    // Role
    @GetMapping("roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("roles/{id}")
    public Role getRole(@PathVariable int id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("roles")
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @PutMapping("roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("roles/{id}")
    public String deleteRole(@PathVariable int id) {
        return roleService.deleteRole(id);
    }

    // Statut

       @GetMapping("statuts")
    public List<Statut> getStatuts() {
        return statutService.getAllStatuts();
    }

    @GetMapping("statuts/{id}")
    public Statut getStatut(@PathVariable int id) {
        return statutService.getStatutById(id);
    }

    @PostMapping("statuts")
    public Statut addStatut(@RequestBody Statut statut) {
        return statutService.addStatut(statut);
    }

    @PutMapping("statuts/{id}")
    public ResponseEntity<Statut> updateStatut(@PathVariable int id, @RequestBody Statut statut) {
        Statut updatedStatut = statutService.updateStatut(id, statut);
        return ResponseEntity.ok(updatedStatut);
    }

    @DeleteMapping("statuts/{id}")
    public String deleteStatut(@PathVariable int id) {
        return statutService.deleteStatut(id);
    }

    // Vehicule

    @GetMapping("vehicules")
public List<Vehicule> getVehicules() {
    return vehiculeService.getAllVehicules();
}

@GetMapping("vehicules/{id}")
public Vehicule getVehicule(@PathVariable int id) {
    return vehiculeService.getVehiculeById(id);
}

@PostMapping("vehicules")
public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
    return vehiculeService.addVehicule(vehicule);
}

@PutMapping("vehicules/{id}")
public ResponseEntity<Vehicule> updateVehicule(@PathVariable int id, @RequestBody Vehicule vehicule) {
    Vehicule updatedVehicule = vehiculeService.updateVehicule(id, vehicule);
    return ResponseEntity.ok(updatedVehicule);
}

@DeleteMapping("vehicules/{id}")
public String deleteVehicule(@PathVariable int id) {
    return vehiculeService.deleteVehicule(id);
}

// VehiculeType

@GetMapping("vehiculetypes")
public List<VehiculeType> getVehiculeTypes() {
    return vehiculeTypeService.getAllVehiculeTypes();
}

@GetMapping("vehiculetypes/{id}")
public VehiculeType getVehiculeType(@PathVariable int id) {
    return vehiculeTypeService.getVehiculeTypeById(id);
}

@PostMapping("vehiculetypes")
public VehiculeType addVehiculeType(@RequestBody VehiculeType vehiculeType) {
    return vehiculeTypeService.addVehiculeType(vehiculeType);
}

@PutMapping("vehiculetypes/{id}")
public ResponseEntity<VehiculeType> updateVehiculeType(@PathVariable int id, @RequestBody VehiculeType vehiculeType) {
    VehiculeType updatedVehiculeType = vehiculeTypeService.updateVehiculeType(id, vehiculeType);
    return ResponseEntity.ok(updatedVehiculeType);
}

@DeleteMapping("vehiculetypes/{id}")
public String deleteVehiculeType(@PathVariable int id) {
    return vehiculeTypeService.deleteVehiculeType(id);
}

}
