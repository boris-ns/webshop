package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_category_discounts")
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @OneToOne
    private ProductCategory category;

    public ProductCategoryDiscount(ProductCategory category, Date from, Date to) {
        this.category = category;
        this.dateFrom = from;
        this.dateTo = to;
    }
}
