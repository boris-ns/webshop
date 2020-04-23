package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stores")
@NoArgsConstructor
@Getter
@Setter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "frequent_buyer_discount", nullable = false)
    private Float frequentBuyerDiscount;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
