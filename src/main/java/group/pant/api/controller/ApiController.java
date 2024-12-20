package group.pant.api.controller;

import group.pant.api.model.Utilisateur;
import group.pant.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping()
    public String hello() {
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

    @PutMapping("utilisateur/{id}")
    public ResponseEntity<HttpStatus> updateUtilisateur(@PathVariable int id, @RequestBody Map<String, Object> newData) {
        utilisateurService.updateUtilisateur(id, newData);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("utilisateur/{id}")
    public ResponseEntity<HttpStatus> patchUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        utilisateurService.changeUtilisateur(utilisateur);
        return ResponseEntity.ok(HttpStatus.OK);
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
}
