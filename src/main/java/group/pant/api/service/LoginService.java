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

    public Login saveLogin(Login login) {
        return loginRepository.save(login);
    }

    public Login getLoginById(Integer id) {
        Optional<Login> login = loginRepository.findById(id);
        return login.orElse(null);
    }

    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }
    
    public Login addLogin(Login login) {
        loginRepository.save(login);
        return login;
    }

    public Login updateLogin(Integer id, Login loginDetails) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            login.setLogin(loginDetails.getLogin());
            login.setMotDePasse(loginDetails.getMotDePasse());
            return loginRepository.save(login);
        } else {
            return null;
        }
    }

    public String deleteLogin(Integer id) {
        loginRepository.deleteById(id);
        return "Deleted Login";
    }

    public Login patchLogin(Integer id, Login loginDetails) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            if (loginDetails.getLogin() != null) {
                login.setLogin(loginDetails.getLogin());
            }
            if (loginDetails.getMotDePasse() != null) {
                login.setMotDePasse(loginDetails.getMotDePasse());
            }
            return loginRepository.save(login);
        } else {
            return null;
        }
    }
}
