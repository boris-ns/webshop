package rs.ac.uns.ftn.webshopservice.config.beans.cepsession;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kie.api.runtime.KieSession;

@AllArgsConstructor
@Getter
public class CepSession {

    private KieSession cepSession;
}
