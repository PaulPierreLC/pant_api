package group.pant.api.service;

import group.pant.api.model.Login;
import group.pant.api.repository.LoginRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;;

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

    public Login patchLogin(int id, Map<String, Object> updates) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "login":
                        login.setLogin((String) value);
                        break;
                    case "motDePasse":
                        login.setMotDePasse((String) value);
                        break;
                    // Add more fields as needed
                }
            });
            return loginRepository.save(login);
        } else {
            throw new EntityNotFoundException("Login not found with id " + id);
        }
    }
}
