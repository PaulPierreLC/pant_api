package group.pant.api.service;

import group.pant.api.model.Login;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.LoginRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
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
}
