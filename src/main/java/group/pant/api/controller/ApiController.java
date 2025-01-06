package group.pant.api.controller;

import group.pant.api.model.Cuisine;
import group.pant.api.model.Plat;
import group.pant.api.model.Restaurant;
import group.pant.api.model.Utilisateur;
import group.pant.api.service.CuisineService;
import group.pant.api.service.PlatService;
import group.pant.api.service.RestaurantService;
import group.pant.api.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ApiController {

    private final UtilisateurService utilisateurService;
    private final PlatService platService;
    private final RestaurantService restaurantService;
    private final CuisineService cuisineService;

    public ApiController(
            UtilisateurService utilisateurService,
            PlatService platService,
            RestaurantService restaurantService,
            CuisineService cuisineService
    ) {
        this.utilisateurService = utilisateurService;
        this.platService = platService;
        this.restaurantService = restaurantService;
        this.cuisineService = cuisineService;
    }

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
}
