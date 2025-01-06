package group.pant.api.controller;

import group.pant.api.model.Plat;
import group.pant.api.model.Utilisateur;
import group.pant.api.model.Ville;
import group.pant.api.service.PlatService;
import group.pant.api.service.UtilisateurService;
import group.pant.api.service.VilleService;
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
    private final VilleService villeService;

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
    public Plat addPlat (@RequestBody Plat plat) {
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

}
