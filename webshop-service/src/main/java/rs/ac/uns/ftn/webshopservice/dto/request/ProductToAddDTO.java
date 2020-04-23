package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductToAddDTO {

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
    private String coupon;
    private Float couponDiscount;
    private Long categoryId;
}
