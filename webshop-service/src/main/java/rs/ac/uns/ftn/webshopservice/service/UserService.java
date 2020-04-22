package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.response.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
}
