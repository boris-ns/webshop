package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.Order;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private String date;
    private Double newPrice;
    private Double originalPrice;
    private ProductDTO product;
    private Integer quantity;
    private Float discount;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.date = order.getDate().toString();
        this.newPrice = order.getPrice();
        this.originalPrice = order.getProduct().getPrice();
        this.product = new ProductDTO(order.getProduct());
        this.quantity = order.getQuantity();
        this.discount = order.getDiscount();
    }
}
