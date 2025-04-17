package group.pant.api.controller;

import group.pant.api.model.*;
import group.pant.api.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final LogService logService;
    private final CommandeDetailService commandeDetailService;
    private final CommandeService commandeService;
    private final CommandeStatutService commandeStatutService;
    private final PaiementEtatService paiementEtatService;
    private final PaiementService paiementService;
    private final PaiementTypeService paiementTypeService;
    private final PlatCuisineService platCuisineService;
    private final PlatRegimeService platRegimeService;
    private final RegimeService regimeService;
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

    @DeleteMapping("utilisateurs/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("Utilisateur with id " + id + " deleted");
    }

    @PutMapping("utilisateurs/{id}")
    public Utilisateur updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    @PatchMapping("utilisateurs/{id}")
    public Utilisateur patchUtilisateur(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return utilisateurService.patchUtilisateur(id, patch);
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
    public ResponseEntity<String> deletePlat(@PathVariable int id) {
        platService.deletePlat(id);
        return ResponseEntity.ok("Plat with id " + id + " deleted");
    }

    @PutMapping("plats/{id}")
    public Plat updatePlat(@PathVariable int id, @RequestBody Plat plat) {
        return platService.updatePlat(id, plat);
    }

    @PatchMapping("plats/{id}")
    public Plat patchPlat(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return platService.patchPlat(id, patch);
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
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant with id " + id + " deleted");
    }

    @PutMapping("restaurants/{id}")
    public Restaurant updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @PatchMapping("restaurants/{id}")
    public Restaurant patchRestaurant(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return restaurantService.patchRestaurant(id, patch);
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
    public ResponseEntity<String> deleteCuisine(@PathVariable int id) {
        cuisineService.deleteCuisine(id);
        return ResponseEntity.ok("Cuisine with id " + id + " deleted");
    }

    @PutMapping("cuisines/{id}")
    public Cuisine updateCuisine(@PathVariable int id, @RequestBody Cuisine cuisine) {
        return cuisineService.updateCuisine(id, cuisine);
    }

    @PatchMapping("cuisines/{id}")
    public Cuisine patchCuisine(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return cuisineService.patchCuisine(id, patch);
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

    @DeleteMapping("actions/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable int id) {
        actionService.deleteAction(id);
        return ResponseEntity.ok("Action with id " + id + " deleted");
    }

    @PutMapping("actions/{id}")
    public Action updateAction(@PathVariable int id, @RequestBody Action action) {
        return actionService.updateAction(id, action);
    }

    @PatchMapping("actions/{id}")
    public Action patchAction(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return actionService.patchAction(id, patch);
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

    @DeleteMapping("adresses/{id}")
    public ResponseEntity<String> deleteAdresse(@PathVariable int id) {
        adresseService.deleteAdresse(id);
        return ResponseEntity.ok("Adresse with id " + id + " deleted");
    }

    @PutMapping("adresses/{id}")
    public Adresse updateAdresse(@PathVariable int id, @RequestBody Adresse adresse) {
        return adresseService.updateAdresse(id, adresse);
    }

    @PatchMapping("adresses/{id}")
    public Adresse patchAdresse(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return adresseService.patchAdresse(id, patch);
    }

    // Avis

    @GetMapping("avis")
    public List<Avis> getAviss() {
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

    @DeleteMapping("avis/{id}")
    public ResponseEntity<String> deleteAvis(@PathVariable int id) {
        avisService.deleteAvis(id);
        return ResponseEntity.ok("Avis with id " + id + " deleted");
    }

    @PutMapping("avis/{id}")
    public Avis updateAvis(@PathVariable int id, @RequestBody Avis avis) {
        return avisService.updateAvis(id, avis);
    }

    @PatchMapping("aviss/{id}")
    public Avis patchAvis(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return avisService.patchAvis(id, patch);
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

    @DeleteMapping("reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation with id " + id + " deleted");
    }

    @PutMapping("reservations/{id}")
    public Reservation updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @PatchMapping("reservations/{id}")
    public Reservation patchReservation(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return reservationService.patchReservation(id, patch);
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

    @DeleteMapping("roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role with id " + id + " deleted");
    }

    @PutMapping("roles/{id}")
    public Role updateRole(@PathVariable int id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @PatchMapping("roles/{id}")
    public Role patchRole(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return roleService.patchRole(id, patch);
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

    @DeleteMapping("statuts/{id}")
    public ResponseEntity<String> deleteStatut(@PathVariable int id) {
        statutService.deleteStatut(id);
        return ResponseEntity.ok("Statut with id " + id + " deleted");
    }

    @PutMapping("statuts/{id}")
    public Statut updateStatut(@PathVariable int id, @RequestBody Statut statut) {
        return statutService.updateStatut(id, statut);
    }

    @PatchMapping("statuts/{id}")
    public Statut patchStatut(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return statutService.patchStatut(id, patch);
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

    @DeleteMapping("vehicules/{id}")
    public ResponseEntity<String> deleteVehicule(@PathVariable int id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.ok("Vehicule with id " + id + " deleted");
    }

    @PutMapping("vehicules/{id}")
    public Vehicule updateVehicule(@PathVariable int id, @RequestBody Vehicule vehicule) {
        return vehiculeService.updateVehicule(id, vehicule);
    }

    @PatchMapping("vehicules/{id}")
    public Vehicule patchVehicule(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return vehiculeService.patchVehicule(id, patch);
    }

    // VehiculeType

    @GetMapping("vehiculeTypes")
    public List<VehiculeType> getVehiculeTypes() {
        return vehiculeTypeService.getAllVehiculeTypes();
    }

    @GetMapping("vehiculeTypes/{id}")
    public VehiculeType getVehiculeType(@PathVariable int id) {
        return vehiculeTypeService.getVehiculeTypeById(id);
    }

    @PostMapping("vehiculeTypes")
    public VehiculeType addVehiculeType(@RequestBody VehiculeType vehiculeType) {
        return vehiculeTypeService.addVehiculeType(vehiculeType);
    }

    @DeleteMapping("vehiculeTypes/{id}")
    public ResponseEntity<String> deleteVehiculeType(@PathVariable int id) {
        vehiculeTypeService.deleteVehiculeType(id);
        return ResponseEntity.ok("VehiculeType with id " + id + " deleted");
    }

    @PutMapping("vehiculeTypes/{id}")
    public VehiculeType updateVehiculeType(@PathVariable int id, @RequestBody VehiculeType vehiculeType) {
        return vehiculeTypeService.updateVehiculeType(id, vehiculeType);
    }

    @PatchMapping("vehiculeTypes/{id}")
    public VehiculeType patchVehiculeType(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return vehiculeTypeService.patchVehiculeType(id, patch);
    }

    //Log

    @GetMapping("logs")
    public List<Log> getLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("logs/{id}")
    public Log getLog(@PathVariable int id) {
        return logService.getLogById(id);
    }

    @PostMapping("logs")
    public Log addLog(@RequestBody Log log) {
        return logService.addLog(log);
    }

    @DeleteMapping("logs/{id}")
    public ResponseEntity<String> deleteLog(@PathVariable int id) {
        logService.deleteLog(id);
        return ResponseEntity.ok("Log with id " + id + " deleted");
    }

    @PutMapping("logs/{id}")
    public Log updateLog(@PathVariable int id, @RequestBody Log log) {
        return logService.updateLog(id, log);
    }

    @PatchMapping("logs/{id}")
    public Log patchLog(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return logService.patchLog(id, patch);
    }
    
    // CommandeDetail

    @GetMapping("commandeDetails")
    public List<CommandeDetail> getCommandeDetails() {
        return commandeDetailService.getAllCommandeDetails();
    }

    @GetMapping("commandeDetails/{id}")
    public CommandeDetail getCommandeDetail(@PathVariable int id) {
        return commandeDetailService.getCommandeDetailById(id);
    }

    @PostMapping("commandeDetails")
    public CommandeDetail addCommandeDetail(@RequestBody CommandeDetail commandeDetail) {
        return commandeDetailService.addCommandeDetail(commandeDetail);
    }

    @DeleteMapping("commandeDetails/{id}")
    public ResponseEntity<String> deleteCommandeDetail(@PathVariable int id) {
        commandeDetailService.deleteCommandeDetail(id);
        return ResponseEntity.ok("CommandeDetail with id " + id + " deleted");
    }

    @PutMapping("commandeDetails/{id}")
    public CommandeDetail updateCommandeDetail(@PathVariable int id, @RequestBody CommandeDetail commandeDetail) {
        return commandeDetailService.updateCommandeDetail(id, commandeDetail);
    }

    @PatchMapping("commandeDetails/{id}")
    public CommandeDetail patchCommandeDetail(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return commandeDetailService.patchCommandeDetail(id, patch);
    }

    // Commande

    @GetMapping("commandes")
    public List<Commande> getCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("commandes/{id}")
    public Commande getCommande(@PathVariable int id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping("commandes")
    public Commande addCommande(@RequestBody Commande commande) {
        return commandeService.addCommande(commande);
    }

    @DeleteMapping("commandes/{id}")
    public ResponseEntity<String> deleteCommande(@PathVariable int id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.ok("Commande with id " + id + " deleted");
    }

    @PutMapping("commandes/{id}")
    public Commande updateCommande(@PathVariable int id, @RequestBody Commande commande) {
        return commandeService.updateCommande(id, commande);
    }

    @PatchMapping("commandes/{id}")
    public Commande patchCommande(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return commandeService.patchCommande(id, patch);
    }

    // CommandeStatut

    @GetMapping("commandeStatuts")
    public List<CommandeStatut> getCommandeStatuts() {
        return commandeStatutService.getAllCommandeStatuts();
    }

    @GetMapping("commandeStatuts/{id}")
    public CommandeStatut getCommandeStatut(@PathVariable int id) {
        return commandeStatutService.getCommandeStatutById(id);
    }

    @PostMapping("commandeStatuts")
    public CommandeStatut addCommandeStatut(@RequestBody CommandeStatut commandeStatut) {
        return commandeStatutService.addCommandeStatut(commandeStatut);
    }

    @DeleteMapping("commandeStatuts/{id}")
    public ResponseEntity<String> deleteCommandeStatut(@PathVariable int id) {
        commandeStatutService.deleteCommandeStatut(id);
        return ResponseEntity.ok("CommandeStatut with id " + id + " deleted");
    }

    @PutMapping("commandeStatuts/{id}")
    public CommandeStatut updateCommandeStatut(@PathVariable int id, @RequestBody CommandeStatut commandeStatut) {
        return commandeStatutService.updateCommandeStatut(id, commandeStatut);
    }

    @PatchMapping("commandeStatuts/{id}")
    public CommandeStatut patchCommandeStatut(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return commandeStatutService.patchCommandeStatut(id, patch);
    }

    // PaiementEtat

    @GetMapping("paiementEtats")
    public List<PaiementEtat> getPaiementEtats() {
        return paiementEtatService.getAllPaiementEtats();
    }

    @GetMapping("paiementEtats/{id}")
    public PaiementEtat getPaiementEtat(@PathVariable int id) {
        return paiementEtatService.getPaiementEtatById(id);
    }

    @PostMapping("paiementEtats")
    public PaiementEtat addPaiementEtat(@RequestBody PaiementEtat paiementEtat) {
        return paiementEtatService.addPaiementEtat(paiementEtat);
    }

    @DeleteMapping("paiementEtats/{id}")
    public ResponseEntity<String> deletePaiementEtat(@PathVariable int id) {
        paiementEtatService.deletePaiementEtat(id);
        return ResponseEntity.ok("PaiementEtat with id " + id + " deleted");
    }

    @PutMapping("paiementEtats/{id}")
    public PaiementEtat updatePaiementEtat(@PathVariable int id, @RequestBody PaiementEtat paiementEtat) {
        return paiementEtatService.updatePaiementEtat(id, paiementEtat);
    }

    @PatchMapping("paiementEtats/{id}")
    public PaiementEtat patchPaiementEtat(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return paiementEtatService.patchPaiementEtat(id, patch);
    }

    // Paiement

    @GetMapping("paiements")
    public List<Paiement> getPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("paiements/{id}")
    public Paiement getPaiement(@PathVariable int id) {
        return paiementService.getPaiementById(id);
    }

    @PostMapping("paiements")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @DeleteMapping("paiements/{id}")
    public ResponseEntity<String> deletePaiement(@PathVariable int id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.ok("Paiement with id " + id + " deleted");
    }

    @PutMapping("paiements/{id}")
    public Paiement updatePaiement(@PathVariable int id, @RequestBody Paiement paiement) {
        return paiementService.updatePaiement(id, paiement);
    }

    @PatchMapping("paiements/{id}")
    public Paiement patchPaiement(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return paiementService.patchPaiement(id, patch);
    }

    // PaiementType

    @GetMapping("paiementTypes")
    public List<PaiementType> getPaiementTypes() {
        return paiementTypeService.getAllPaiementTypes();
    }

    @GetMapping("paiementTypes/{id}")
    public PaiementType getPaiementType(@PathVariable int id) {
        return paiementTypeService.getPaiementTypeById(id);
    }

    @PostMapping("paiementTypes")
    public PaiementType addPaiementType(@RequestBody PaiementType paiementType) {
        return paiementTypeService.addPaiementType(paiementType);
    }

    @DeleteMapping("paiementTypes/{id}")
    public ResponseEntity<String> deletePaiementType(@PathVariable int id) {
        paiementTypeService.deletePaiementType(id);
        return ResponseEntity.ok("PaiementType with id " + id + " deleted");
    }

    @PutMapping("paiementTypes/{id}")
    public PaiementType updatePaiementType(@PathVariable int id, @RequestBody PaiementType paiementType) {
        return paiementTypeService.updatePaiementType(id, paiementType);
    }

    @PatchMapping("paiementTypes/{id}")
    public PaiementType patchPaiementType(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return paiementTypeService.patchPaiementType(id, patch);
    }

    // PlatCuisine

    @GetMapping("platCuisines")
    public List<PlatCuisine> getPlatCuisines() {
        return platCuisineService.getAllPlatCuisines();
    }

    @GetMapping("platCuisines/{id}")
    public PlatCuisine getPlatCuisine(@PathVariable int id) {
        return platCuisineService.getPlatCuisineById(id);
    }

    @PostMapping("platCuisines")
    public PlatCuisine addPlatCuisine(@RequestBody PlatCuisine platCuisine) {
        return platCuisineService.addPlatCuisine(platCuisine);
    }

    @DeleteMapping("platCuisines/{id}")
    public ResponseEntity<String> deletePlatCuisine(@PathVariable int id) {
        platCuisineService.deletePlatCuisine(id);
        return ResponseEntity.ok("PlatCuisine with id " + id + " deleted");
    }

    @PutMapping("platCuisines/{id}")
    public PlatCuisine updatePlatCuisine(@PathVariable int id, @RequestBody PlatCuisine platCuisine) {
        return platCuisineService.updatePlatCuisine(id, platCuisine);
    }

    @PatchMapping("platCuisines/{id}")
    public PlatCuisine patchPlatCuisine(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return platCuisineService.patchPlatCuisine(id, patch);
    }

    // Regime

    @GetMapping("regimes")
    public List<Regime> getRegimes() {
        return regimeService.getAllRegimes();
    }

    @GetMapping("regimes/{id}")
    public Regime getRegime(@PathVariable int id) {
        return regimeService.getRegimeById(id);
    }

    @PostMapping("regimes")
    public Regime addRegime(@RequestBody Regime regime) {
        return regimeService.addRegime(regime);
    }

    @DeleteMapping("regimes/{id}")
    public ResponseEntity<String> deleteRegime(@PathVariable int id) {
        regimeService.deleteRegime(id);
        return ResponseEntity.ok("Regime with id " + id + " deleted");
    }

    @PutMapping("regimes/{id}")
    public Regime updateRegime(@PathVariable int id, @RequestBody Regime regime) {
        return regimeService.updateRegime(id, regime);
    }

    @PatchMapping("regimes/{id}")
    public Regime patchRegime(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return regimeService.patchRegime(id, patch);
    }

    // PlatRegime

    @GetMapping("platRegimes")
    public List<PlatRegime> getPlatRegimes() {
        return platRegimeService.getAllPlatRegimes();
    }

    @GetMapping("platRegimes/{id}")
    public PlatRegime getPlatRegime(@PathVariable int id) {
        return platRegimeService.getPlatRegimeById(id);
    }

    @PostMapping("platRegimes")
    public PlatRegime addPlatRegime(@RequestBody PlatRegime platRegime) {
        return platRegimeService.addPlatRegime(platRegime);
    }

    @DeleteMapping("platRegimes/{id}")
    public ResponseEntity<String> deletePlatRegime(@PathVariable int id) {
        platRegimeService.deletePlatRegime(id);
        return ResponseEntity.ok("PlatRegime with id " + id + " deleted");
    }

    @PutMapping("platRegimes/{id}")
    public PlatRegime updatePlatRegime(@PathVariable int id, @RequestBody PlatRegime platRegime) {
        return platRegimeService.updatePlatRegime(id, platRegime);
    }

    @PatchMapping("platRegimes/{id}")
    public PlatRegime patchPlatRegime(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return platRegimeService.patchPlatRegime(id, patch);
    }

    // Ville

    @GetMapping("villes")
    public List<Ville> getVilles() {
        return villeService.getAllVilles();
    }

    @GetMapping("villes/{id}")
    public Ville getVille(@PathVariable int id) {
        return villeService.getVilleById(id);
    }

    @PostMapping("villes")
    public Ville addVille(@RequestBody Ville ville) {
        return villeService.addVille(ville);
    }

    @DeleteMapping("villes/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        villeService.deleteVille(id);
        return ResponseEntity.ok("Ville with id " + id + " deleted");
    }

    @PutMapping("villes/{id}")
    public Ville updateVille(@PathVariable int id, @RequestBody Ville ville) {
        return villeService.updateVille(id, ville);
    }

    @PatchMapping("villes/{id}")
    public Ville patchVille(@PathVariable int id, @RequestBody Map<String, Object> patch) {
        return villeService.patchVille(id, patch);
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
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(login.getUtilisateur().getId());
    login.setUtilisateur(utilisateur);
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

    @PostMapping("connexion")
    public ResponseEntity<String> login(@RequestBody Map<String, String> payload, HttpServletResponse response) {
        try {
            String login = payload.get("login");
            String motDePasse = payload.get("motDePasse");

            Login authenticatedLogin = loginService.authenticate(login, motDePasse);

            Utilisateur utilisateur = authenticatedLogin.getUtilisateur();

            Cookie userCookie = new Cookie("userId", utilisateur.getId().toString());
            userCookie.setHttpOnly(true); 
            userCookie.setPath("/"); 
            userCookie.setMaxAge(60 * 60 * 24); 
            response.addCookie(userCookie);

            return ResponseEntity.ok("Connexion réussie !");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou mot de passe incorrect");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}