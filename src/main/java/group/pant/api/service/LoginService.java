package group.pant.api.service;

import group.pant.api.model.Login;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.LoginRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;


import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;;

    private final UtilisateurRepository utilisateurRepository;

    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    public Login getLoginById(int id) {
        return loginRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Login with id " + id + " not found"));
    }

    public Login addLogin(Login login) {
        loginRepository.save(login);
        return login;
    }

    public String deleteLogin(int id) {
        loginRepository.deleteById(id);
        return "Deleted Login";
    }

    public Login updateLogin(int id, Login login) {
        login.setId(id);
        return loginRepository.save(login);
    }

    public Login createLogin(Login login) {
        Utilisateur utilisateur = utilisateurRepository.findById(login.getUtilisateur().getId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + login.getUtilisateur().getId()));
        login.setUtilisateur(utilisateur);
        return loginRepository.save(login);
    }


    public Login patchLogin(int id, Map<String, Object> patch) {
        Login existingLogin = loginRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Login with id " + id + " not found"));
        patch.forEach((key, value) -> {
            switch (key) {
                case "idRestaurant":
                    if (value instanceof Map<?, ?> restaurantMap) {
                        Integer utilisateurId = (Integer) restaurantMap.get("id");
                        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + utilisateurId + " not found"));
                        existingLogin.setUtilisateur(utilisateur);
                    }
                    break;
                case "login":
                    existingLogin.setLogin((String) value);
                    break;
                case "motDePasse":
                    existingLogin.setMotDePasse((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        return loginRepository.save(existingLogin);

    }

    public Login getLoginByUsername(String username) {
        return loginRepository.findByLogin(username)
                .orElseThrow(() -> new EntityNotFoundException("Login with username " + username + " not found"));
    }

    public Login save(Login login) {
        if (login.getUtilisateur() == null || login.getUtilisateur().getId() == null) {
            throw new IllegalArgumentException("Un login doit être associé à un utilisateur existant.");
        }
        try {
            return loginRepository.save(login);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du login : " + e.getMessage(), e);
        }

    }

    public Login authenticate(String login, String motDePasse) {
        Login existingLogin = loginRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Login non trouvé"));
    
        if (!existingLogin.getMotDePasse().equals(motDePasse)) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }
    
        return existingLogin;
    }

    public Cookie handleLogin(String login, String motDePasse, HttpServletResponse response) {
        Login authenticatedLogin = authenticate(login, motDePasse);

        Utilisateur utilisateur = authenticatedLogin.getUtilisateur();

        Cookie userCookie = new Cookie("userId", utilisateur.getId().toString());
        userCookie.setHttpOnly(true);
        userCookie.setPath("/");
        userCookie.setMaxAge(60 * 60 * 24); // 1 jour

        return userCookie;
    }
}