package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class EditStoreDTO {

    private String name;

    @Min(value = 0, message = "Min value for frequent buyer discount is 0")
    @Max(value = 100, message = "Max value for frequent buyer discount is 100")
    private Float frequentBuyerDiscount;
}
