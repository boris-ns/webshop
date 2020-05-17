package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class PlaceOrderDTO {

    @NotNull(message = "Product ID is required")
    public Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Minimum value must be 1")
    public Integer quantity;

    public String coupon;

}
