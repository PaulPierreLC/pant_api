package group.pant.api.controller;

import group.pant.api.model.*;
import group.pant.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final LogService logService;
    private final VilleService villeService;
    private final LoginService loginService;
    

    @GetMapping()
    public String accueil() {
        return "Bienvenue dans l'API de PANToplate";
    }

    // Utilisateur

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

    // Plat

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

    // Restaurant

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

    // Cuisine

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
        return actionService.getActionById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));
    }

    @PutMapping("actions/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable int id, @RequestBody Action action) {
        Action updatedAction = actionService.updateAction(id, action);
        return ResponseEntity.ok(updatedAction);
    }
    @PatchMapping("actions/{id}")
    public ResponseEntity<Action> patchAction(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        Action patchedAction = actionService.patchAction(id, patch);
        return ResponseEntity.ok(patchedAction);
    }

    @PostMapping("actions")
    public Action createAction(@RequestBody Action action) {
        return actionService.createAction(action);
    }

    @DeleteMapping("actions/{id}")
    public String deleteAction(@PathVariable int id) {
        return actionService.deleteAction(id); // Now it returns a String
    }

    // Adresse

    @GetMapping("adresses")
    public List<Adresse> getAdresses() {
        return adresseService.getAllAdresses();
    }

    @GetMapping("adresses/{id}")
    public Adresse getAdresse(@PathVariable int id) {
        return adresseService.getAdresseById(id)
                .orElseThrow(() -> new RuntimeException("Adresse not found"));
    }

    @PostMapping("adresses")
    public Adresse addAdresse(@RequestBody Adresse adresse) {
        return adresseService.createAdresse(adresse);
    }

    @PatchMapping("adresses/{id}")
    public ResponseEntity<Adresse> patchAdresse(@PathVariable Integer id, @RequestBody Map<String, Object> patch) {
        Adresse patchedAdresse = adresseService.patchAdresse(id, patch);
        return ResponseEntity.ok(patchedAdresse);
    }

    @PutMapping("adresses/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable int id, @RequestBody Adresse adresse) {
        Adresse updatedAdresse = adresseService.updateAdresse(id, adresse);
        return ResponseEntity.ok(updatedAdresse);
    }

    @DeleteMapping("adresses/{id}")
    public ResponseEntity<String> deleteAdresse(@PathVariable int id) {
        adresseService.deleteAdresse(id); // No need to return anything from service method
        return ResponseEntity.ok("Adresse deleted"); // You return a ResponseEntity with a String message
    }

    // Avis

    @GetMapping("avis")
    public List<Avis> getAvis() {
    return avisService.getAllAvis();
}

    @GetMapping("avis/{id}")
    public Avis getAvis(@PathVariable Integer id) {
        // Use Optional to retrieve the Avis, throwing an exception if not found
        return avisService.getAvisById(id).orElseThrow(() -> new RuntimeException("Avis not found"));
    }
    @PostMapping("avis")
    public Avis createAvis(@RequestBody Avis avis) {
        return avisService.createAvis(avis);
    }

    @PutMapping("avis/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable int id, @RequestBody Avis avis) {
    Avis updatedAvis = avisService.updateAvis(id, avis);
    return ResponseEntity.ok(updatedAvis);
    }

    @DeleteMapping("avis/{id}")
    public ResponseEntity<String> deleteAvis(@PathVariable Integer id) {
        avisService.deleteAvis(id);  // Assuming this method is void
        return ResponseEntity.ok("Avis deleted");  // Returning ResponseEntity with success message
    }

    @PatchMapping("avis/{id}")
    public Avis patchAvis(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        return avisService.patchAvis(id, updates);
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
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PatchMapping("reservations/{id}")
    public ResponseEntity<Reservation> patchReservation(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Reservation updatedReservation = reservationService.patchReservation(id, updates);
        return ResponseEntity.ok(updatedReservation);
    }

    @PutMapping("reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation deleted");
    }

    // Role

    @GetMapping("roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("roles/{id}")
    public Role getRole(@PathVariable int id) {
        return roleService.getRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @PostMapping("roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PatchMapping("roles/{id}")
    public ResponseEntity<Role> patchRole(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Role updatedRole = roleService.patchRole(id, updates);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("roles/{id}")
    public String deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return "Role deleted";
    }

    // Statut

    @GetMapping("statuts")
    public List<Statut> getStatuts() {
        return statutService.getAllStatuts();
    }

    @GetMapping("statuts/{id}")
    public Statut getStatut(@PathVariable int id) {
        return statutService.getStatutById(id)
                .orElseThrow(() -> new RuntimeException("Statut not found"));
    }


    @PostMapping("statuts")
    public ResponseEntity<Statut> createStatut(@RequestBody Statut statut) {
        Statut createdStatut = statutService.createStatut(statut);
        return new ResponseEntity<>(createdStatut, HttpStatus.CREATED);
    }

    @PatchMapping("statuts/{id}")
    public ResponseEntity<Statut> patchStatut(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> updates) {
        Statut updatedStatut = statutService.patchStatut(id, updates);
        return ResponseEntity.ok(updatedStatut);
    }

    @PutMapping("statuts/{id}")
    public ResponseEntity<Statut> updateStatut(@PathVariable int id, @RequestBody Statut statut) {
        Statut updatedStatut = statutService.updateStatut(id, statut);
        return ResponseEntity.ok(updatedStatut);
    }

    @DeleteMapping("statuts/{id}")
    public String deleteStatut(@PathVariable int id) {
        statutService.deleteStatut(id);
        return "Statut deleted";
    }

    // Vehicule

    @GetMapping("vehicules")
    public List<Vehicule> getVehicules() {
    return vehiculeService.getAllVehicules();
}

    @GetMapping("vehicules/{id}")
    public ResponseEntity<Vehicule> getVehicule(@PathVariable int id) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));
        return ResponseEntity.ok(vehicule);
    }


    @PostMapping("vehicules")
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        Vehicule createdVehicule = vehiculeService.createVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicule);
    }

    @PatchMapping("vehicules/{id}")
    public ResponseEntity<Vehicule> patchVehicule(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> updates) {
        Vehicule patchedVehicule = vehiculeService.patchVehicule(id, updates);
        return ResponseEntity.ok(patchedVehicule);
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
        return vehiculeTypeService.getVehiculeTypeById(id)
                .orElseThrow(() -> new RuntimeException("VehiculeType not found"));
    }

    @PostMapping("vehiculetypes")
    public VehiculeType addVehiculeType(@RequestBody VehiculeType vehiculeType) {
        return vehiculeTypeService.createVehiculeType(vehiculeType);
    }

    @PatchMapping("vehiculetypes/{id}")
    public VehiculeType patchVehiculeType(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        return vehiculeTypeService.patchVehiculeType(id, updates);
    }

    @PutMapping("vehiculetypes/{id}")
    public ResponseEntity<VehiculeType> updateVehiculeType(@PathVariable int id, @RequestBody VehiculeType vehiculeType) {
        VehiculeType updatedVehiculeType = vehiculeTypeService.updateVehiculeType(id, vehiculeType);
        return ResponseEntity.ok(updatedVehiculeType);
    }

    @DeleteMapping("vehiculetypes/{id}")
    public ResponseEntity<String> deleteVehiculeType(@PathVariable int id) {
        vehiculeTypeService.deleteVehiculeType(id);
        return ResponseEntity.ok("VehiculeType deleted successfully");
    }

    //Log

    // Créer un nouveau log
    @PostMapping("log")
    public Log createLog(@RequestParam int actionId, @RequestParam int utilisateurId, @RequestBody String parametre) {
        // Récupération de l'action
        Action action = actionService.getActionById(actionId).orElse(null);
        if (action == null) {

            return null;
        }

        // Récupération de l'utilisateur
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(utilisateurId);
        if (utilisateur == null) {
            return null;
        }

        // Créer le log si les entités existent
        return logService.addLog(action, utilisateur, parametre);
    }

    // Récupérer tous les logs
    @GetMapping("log")
    public List<Log> getLogs() {
        return logService.getAllLogs();
    }

    // Récupérer un log par ID
    @GetMapping("log/{id}")
    public Log getLogById(@PathVariable int id) {
        return logService.getLogById(id);
    }

    // Supprimer un log par ID
    @DeleteMapping("log/{id}")
    public String deleteLog(@PathVariable int id) {
        return logService.deleteLog(id);
    }

    // Mettre à jour un log
    @PutMapping("log/{id}")
    public Log updateLog(@PathVariable int id, @RequestBody Log logDetails) {
        return logService.updateLog(id, logDetails);
    }

    // Appliquer un patch à un log
    @PatchMapping("log/{id}")
    public Log patchLog(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return logService.patchLog(id, patch);
    }

    // Ville

    @GetMapping("villes")
    public List<Ville> getVilles() { return villeService.getAllVilles(); }

    @GetMapping("villes/{id}")
    public Ville getVille(@PathVariable int id) { return villeService.getVilleById(id); }

    @PostMapping("villes")
    public Ville addVille (@RequestBody Ville ville) { return villeService.addVille(ville); }

    @DeleteMapping("villes/{id}")
    public String deleteVille(@PathVariable int id) { return villeService.deleteVilleById(id); }

    @PutMapping("villes/{id}")
    public ResponseEntity<Ville> updateVille(@PathVariable int id, @RequestBody Ville ville) {
        Ville updatedVille = villeService.updateVille(id, ville);
        return ResponseEntity.ok(updatedVille);
    }

    @PatchMapping("villes/{id}")
    public ResponseEntity<Ville> patchVille(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        Ville patchedVille = villeService.patchVille(id, patch);
        return ResponseEntity.ok(patchedVille);
    }

    // Login

    @GetMapping("logins")
    public List<Login> getLogins() {
        return loginService.getAllLogins();
    }

    @GetMapping("logins/{id}")
    public Login getLogin(@PathVariable int id) {
        return loginService.getLoginById(id);
    }

    @PostMapping("logins")
    public Login addLogin(@RequestBody Login login) {
        return loginService.addLogin(login);
    }

    @DeleteMapping("logins/{id}")
    public String deleteLogin(@PathVariable int id) {
        return loginService.deleteLogin(id);
    }

    @PutMapping("logins/{id}")
    public ResponseEntity<Login> updateLogin(@PathVariable int id, @RequestBody Login login) {
        Login updatedLogin = loginService.updateLogin(id, login);
        return ResponseEntity.ok(updatedLogin);
    }

    @PatchMapping("logins/{id}")
    public ResponseEntity<Login> patchLogin(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        Login patchedLogin = loginService.patchLogin(id, patch);
        return ResponseEntity.ok(patchedLogin);
    }
}
