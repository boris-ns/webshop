package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.Product;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Double shippingPrice;
    private Integer quantity;
    private Integer maxQuantity;
    private Integer maxOrderQuantity;
    private Float quantityDiscount;
    private Integer orderQuantityDiscount;
    private Float discount;
    private Float maxDiscount;
    private Float couponDiscount;
    private String storeName;
    private String category;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.shippingPrice = product.getShippingPrice();
        this.quantity = product.getQuantity();
        this.maxQuantity = product.getMaxQuantity();
        this.maxOrderQuantity = product.getMaxOrderQuantity();
        this.quantityDiscount = product.getQuantityDiscount();
        this.orderQuantityDiscount = product.getOrderQuantityDiscount();
        this.discount = product.getDiscount();
        this.maxDiscount = product.getMaxDiscount();
        this.couponDiscount = product.getCouponDiscount();
        this.storeName = product.getStore().getName();
        this.category = product.getCategory().getName();
    }
}
