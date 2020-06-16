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
public class AddStoreDTO {

    @NotNull(message = "User is required")
    private UserRegistrationDTO user;

    @NotNull(message = "Store name is required")
    private String name;

    @NotNull(message = "Frequent buyer discount is required")
    @Min(0) @Max(100)
    private Float frequentBuyerDiscount;
}
