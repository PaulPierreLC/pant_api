package group.pant.api.controller;

import group.pant.api.model.Utilisateur;
import group.pant.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("utilisateur/{id}")
    public Utilisateur getUtilisateur(@PathVariable Integer id) {
        return utilisateurService.getUtilisateurById(id);
    }
}
