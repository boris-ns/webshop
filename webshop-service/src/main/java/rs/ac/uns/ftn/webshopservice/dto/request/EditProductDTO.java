package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Double shippingPrice;
    private Integer quantity;
    private Integer maxOrderQuantity;
    private Float quantityDiscount;
    private Integer orderQuantityDiscount; // koliko artikala treba odjednom naruciti da bi se primenio popust
    private Float discount;
    private Float maxDiscount;
    private String coupon;
    private Float couponDiscount;

}
