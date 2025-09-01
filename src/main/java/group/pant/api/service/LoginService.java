package group.pant.api.service;

import group.pant.api.model.Login;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.LoginRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

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
        return loginRepository.findByLoginAndMotDePasse(login, motDePasse)
                .orElseThrow(() -> new EntityNotFoundException("Login ou mot de passe incorrect"));
    }

    public Cookie handleLogin(String login, String motDePasse, HttpServletResponse response) {
        Login authenticatedLogin = authenticate(login, motDePasse);

        Utilisateur utilisateur = authenticatedLogin.getUtilisateur();

        Cookie userCookie = new Cookie("userId", utilisateur.getId().toString());
        userCookie.setHttpOnly(false);
        userCookie.setSecure(false);
        userCookie.setPath("/");
        userCookie.setMaxAge(60 * 60 * 24); // 1 jour
        response.addCookie(userCookie);
        System.out.println("Cookie créé : " + userCookie.getName() + " = " + userCookie.getValue());

        return userCookie;
    }

    public ResponseEntity<String> handleLogin(Map<String, String> payload, HttpSession session) {
        try {
            String login = payload.get("login");
            String motDePasse = payload.get("motDePasse");

            // Authentifier l'utilisateur
            Login authenticatedLogin = authenticate(login, motDePasse);

            // Stocker les informations utilisateur dans la session
            session.setAttribute("userId", authenticatedLogin.getUtilisateur().getId());
            session.setAttribute("username", authenticatedLogin.getLogin());

            // Log de création de session
            System.out.println("Session créée : JSESSIONID=" + session.getId() +
                ", userId=" + session.getAttribute("userId") +
                ", username=" + session.getAttribute("username"));

            return ResponseEntity.ok("Connexion réussie !");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou mot de passe incorrect");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<Map<String, Object>> getSessionInfo(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

        if (userId == null || username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Utilisateur non connecté"));
        }

        return ResponseEntity.ok(Map.of(
            "userId", userId,
            "username", username
        ));
    }
    
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        System.out.println("Session détruite : JSESSIONID=" + session.getId());
        return ResponseEntity.ok("Déconnexion réussie !");
    }
    
    public ResponseEntity<String> handleLogout(HttpSession session) {
        String sessionId = session.getId();
        session.invalidate();
        System.out.println("Session détruite : JSESSIONID=" + sessionId);
        return ResponseEntity.ok("Déconnexion réussie !");
    }
    
}