package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddProductCategoryDiscountDTO {

    @NotNull(message = "Category id is required")
    private Long categoryId;

    @NotNull(message = "Start date is required")
    private int[] from;

    @NotNull(message = "End date is required")
    private int[] to;

    @NotNull(message = "Discount is required")
    @Min(0) @Max(100)
    private Float discount;
}
