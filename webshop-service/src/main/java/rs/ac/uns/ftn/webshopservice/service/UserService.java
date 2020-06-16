package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.UserDTO;
import rs.ac.uns.ftn.webshopservice.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    User addUser(UserRegistrationDTO userInfo);
    User addOwner(UserRegistrationDTO userInfo);
    boolean activateAccount(String token);
}
