package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.webshopservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity add(@Valid @RequestBody UserRegistrationDTO user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public/verify-account/{token}")
    public ResponseEntity verifyUserAccount(@PathVariable String token) {
        userService.activateAccount(token);
        return ResponseEntity.ok().build();
    }
}
