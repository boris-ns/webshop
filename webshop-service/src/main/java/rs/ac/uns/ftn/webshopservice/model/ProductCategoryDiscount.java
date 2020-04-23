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

    @Column(nullable = false)
    private Date from;

    @Column(nullable = false)
    private Date to;

    @OneToOne
    private ProductCategory category;

    public ProductCategoryDiscount(ProductCategory category, Date from, Date to) {
        this.category = category;
        this.from = from;
        this.to = to;
    }
}
