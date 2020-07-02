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

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "discount", nullable = false)
    private Float discount;

    public SeasonDiscount(String name, Date from, Date to, Float discount) {
        this.name = name;
        this.dateFrom = from;
        this.dateTo = to;
        this.discount = discount;
    }
}
