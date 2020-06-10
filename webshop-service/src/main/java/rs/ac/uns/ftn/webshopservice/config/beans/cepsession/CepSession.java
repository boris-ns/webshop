package rs.ac.uns.ftn.webshopservice.config.beans.cepsession;

import lombok.Getter;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.webshopservice.model.events.TransactionEvent;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CepSession {

    private KieSession cepSession;
    private List<TransactionEvent> events;

    public CepSession(KieSession session) {
        this.cepSession = session;
        this.events = new ArrayList<>();
    }
}
