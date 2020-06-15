package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.model.Buyer;
import rs.ac.uns.ftn.webshopservice.model.ConfirmationToken;
import rs.ac.uns.ftn.webshopservice.service.MailSenderService;
import rs.ac.uns.ftn.webshopservice.service.UserService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Override
    public void sendRegistrationMail(ConfirmationToken token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Account verification - WebShop");
        message.setFrom("WebShop-App");
        message.setTo(token.getUser().getEmail());
        message.setText("Go to this page to activate your account http://localhost:4200/verify?token=" + token.getToken());
        mailSender.send(message);
    }

    @Override
    public void sendWarningMail(Long buyerId, String reason) {
        Buyer buyer = (Buyer) userService.findById(buyerId);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Account warning - WebShop");
        message.setFrom("WebShop-App");
        message.setTo(buyer.getEmail());
        message.setText("We detected some problems with usage of your account.\n" +
                reason + "\nCheck your account to see if everything is ok.");
        mailSender.send(message);
    }
}
