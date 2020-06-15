package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.common.TimeProvider;
import rs.ac.uns.ftn.webshopservice.config.consts.UserRoles;
import rs.ac.uns.ftn.webshopservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.UserDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.mappers.UserMapper;
import rs.ac.uns.ftn.webshopservice.model.Buyer;
import rs.ac.uns.ftn.webshopservice.model.ConfirmationToken;
import rs.ac.uns.ftn.webshopservice.model.User;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;
import rs.ac.uns.ftn.webshopservice.repository.AuthorityRepository;
import rs.ac.uns.ftn.webshopservice.repository.ConfirmationTokenRepository;
import rs.ac.uns.ftn.webshopservice.repository.UserRepository;
import rs.ac.uns.ftn.webshopservice.service.MailSenderService;
import rs.ac.uns.ftn.webshopservice.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository tokenRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TimeProvider timeProvider;

    @Override
    public User findById(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("User with id '" + id + "' doesn't exist.");
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ResourceNotFoundException("User with username '" + username + "' doesn't exist.");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(UserRegistrationDTO userInfo) {
        if (userRepository.findByUsername(userInfo.getUsername()) != null) {
            throw new ApiRequestException("Username '" + userInfo.getUsername() + "' already exists.");
        }

        if (!userInfo.getPassword().equals(userInfo.getRepeatPassword())) {
            throw new ApiRequestException("Provided passwords must be the same.");
        }

        if (userRepository.findByEmail(userInfo.getEmail()) != null) {
            throw new ApiRequestException("Email '" + userInfo.getEmail() + "' is taken.");
        }

        User user = createNewUserObject(userInfo);
        userRepository.save(user);

        ConfirmationToken token = new ConfirmationToken(user);
        tokenRepository.save(token);

        mailSenderService.sendRegistrationMail(token);

        return user;
    }

    private User createNewUserObject(UserRegistrationDTO userInfo) {
        Buyer user = UserMapper.toBuyerEntity(userInfo);
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        user.setLastPasswordResetDate(timeProvider.nowTimestamp());
        user.getUserAuthorities().add(authorityRepository.findByName(UserRoles.ROLE_BUYER));
        user.setTotalMoneySpent(0.0);
        user.setCategory(BuyerCategory.REGULAR);

        return user;
    }

    @Override
    public boolean activateAccount(String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token);

        if (confirmationToken == null) {
            throw new ResourceNotFoundException("Confirmation token doesn't exist.");
        }

        if (confirmationToken.isUsed()) {
            throw new ApiRequestException("This token has been already used.");
        }

        User user = confirmationToken.getUser();
        long timeDifference = timeProvider.timeDifferenceInMinutes(timeProvider.now(), confirmationToken.getDatetimeCreated());

        if (timeDifference < 30) {
            user.setEnabled(true);
            userRepository.save(user);
            confirmationToken.setUsed(true);
            tokenRepository.save(confirmationToken);
            return true;
        } else {
            tokenRepository.delete(confirmationToken);
            userRepository.delete(user);
            throw new ApiRequestException("Confirmation token timed out.");
        }
    }
}
