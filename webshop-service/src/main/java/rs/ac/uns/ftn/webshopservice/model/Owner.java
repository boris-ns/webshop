package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("1")
@NoArgsConstructor
@Getter
@Setter
public class Owner extends User {

    @OneToOne(mappedBy = "owner")
    private Store store;
}
