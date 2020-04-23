package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Buyer extends User {

    @Column
    @Enumerated
    private BuyerCategory category;

    @Column(name = "total_money_spent")
    private Double totalMoneySpent;

    @OneToMany
    private Set<Order> orders;
}
