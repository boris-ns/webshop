package rs.ac.uns.ftn.webshopservice.model.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h")
@NoArgsConstructor
@Getter
@Setter
public class TransactionEvent {

    private Date executionTime;
    private Long buyerId;
    private Double price;

    public TransactionEvent(Long buyerId, Double price) {
        this.executionTime = new Date();
        this.buyerId = buyerId;
        this.price = price;
    }
}
