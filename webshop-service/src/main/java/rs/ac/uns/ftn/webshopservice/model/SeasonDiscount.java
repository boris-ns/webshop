package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "season_discounts")
@NoArgsConstructor
@Getter
@Setter
public class SeasonDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date from;

    @Column(nullable = false)
    private Date to;

    public SeasonDiscount(String name, Date from, Date to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }
}
