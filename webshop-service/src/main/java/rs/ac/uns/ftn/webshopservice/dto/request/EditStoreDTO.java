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
public class EditStoreDTO {

    @NotNull(message = "Store id is required")
    private Long storeId;

    private String name;

    @Min(value = 0, message = "Min value for frequent buyer discount is 0")
    @Max(value = 100, message = "Max value for frequent buyer discount is 100")
    private Float frequentBuyerDiscount;
}
