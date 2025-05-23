package group.pant.api.repository;

import group.pant.api.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByLogin(String login);

}

