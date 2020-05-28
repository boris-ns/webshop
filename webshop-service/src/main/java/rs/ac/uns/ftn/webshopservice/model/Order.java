package rs.ac.uns.ftn.webshopservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Float discount;

    @Column
    private String usersCoupon;

    @ManyToOne
    private Product product;

    public Order(Product product, String usersCoupon, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
        this.date = new Date();
        this.discount = 0f;
        this.usersCoupon = usersCoupon;
    }
}
