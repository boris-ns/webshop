package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
