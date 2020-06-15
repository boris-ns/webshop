package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.model.ConfirmationToken;

public interface MailSenderService {

    void sendRegistrationMail(ConfirmationToken token);
    void sendWarningMail(Long buyerId, String reason);
}
