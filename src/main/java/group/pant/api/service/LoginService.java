package group.pant.api.service;

import group.pant.api.model.Login;
import group.pant.api.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // Récupérer tous les logins
    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    // Récupérer un login par ID
    public Optional<Login> getLoginById(Integer id) {
        return loginRepository.findById(id);
    }

    // Créer un nouveau login
    public Login createLogin(Login login) {
        return loginRepository.save(login);
    }

    // Mettre à jour complètement un login
    public Login updateLogin(Integer id, Login loginDetails) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new RuntimeException("Login not found"));
        login.setLogin(loginDetails.getLogin());
        login.setMotDePasse(loginDetails.getMotDePasse());
        login.setParametres(loginDetails.getParametres());
        return loginRepository.save(login);
    }

    // Mettre à jour partiellement un login
    public Login patchLogin(Integer id, Login loginDetails) {
        Login login = loginRepository.findById(id).orElseThrow(() -> new RuntimeException("Login not found"));
        if (loginDetails.getLogin() != null) {
            login.setLogin(loginDetails.getLogin());
        }
        if (loginDetails.getMotDePasse() != null) {
            login.setMotDePasse(loginDetails.getMotDePasse());
        }
        if (loginDetails.getParametres() != null) {
            login.setParametres(loginDetails.getParametres());
        }
        return loginRepository.save(login);
    }

    // Supprimer un login
    public void deleteLogin(Integer id) {
        loginRepository.deleteById(id);
    }
}