package rs.ac.uns.ftn.webshopservice.config.beans.cepsession;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.webshopservice.service.MailSenderService;

@Configuration
public class CepKieSession {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private MailSenderService mailSenderService;

    @Bean
    public CepSession getCepKieSession() {
        KieSession kieSession = kieContainer.newKieSession("CepSession");
        kieSession.setGlobal("g_mailSenderService", mailSenderService);
        return new CepSession(kieSession);
    }
}
