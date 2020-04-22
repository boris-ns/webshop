package rs.ac.uns.ftn.webshopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.webshopservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
