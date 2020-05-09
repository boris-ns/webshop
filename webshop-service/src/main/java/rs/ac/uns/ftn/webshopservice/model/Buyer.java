package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
@NoArgsConstructor
@Getter
@Setter
public class Buyer extends User {

    @Column
    @Enumerated
    private BuyerCategory category;

    @Column(name = "total_money_spent")
    private Double totalMoneySpent;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;
}
