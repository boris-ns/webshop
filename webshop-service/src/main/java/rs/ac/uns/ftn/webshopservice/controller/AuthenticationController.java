package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.request.LoginDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.PasswordChangerDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.UserDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.UserTokenDTO;
import rs.ac.uns.ftn.webshopservice.security.TokenUtils;
import rs.ac.uns.ftn.webshopservice.service.impl.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO authenticationRequest) {
        return new ResponseEntity<>(userDetailsService.login(authenticationRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserTokenDTO> refreshAuthenticationToken(HttpServletRequest request) {
        return new ResponseEntity<>(userDetailsService.refreshAuthenticationToken(request), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestBody @Valid PasswordChangerDTO passwordChanger) {
        userDetailsService.changePassword(passwordChanger.getOldPassword(), passwordChanger.getNewPassword());
        return ResponseEntity.ok().build();
    }

}
