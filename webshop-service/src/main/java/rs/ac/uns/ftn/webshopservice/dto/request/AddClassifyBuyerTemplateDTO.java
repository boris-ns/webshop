package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class AddClassifyBuyerTemplateDTO {

    @NotNull(message = "Old category is required")
    private String buyerCategory;

    @NotNull(message = "New category is required")
    private String newCategory;

    @NotNull(message = "Orders size is required")
    private int ordersSize;

    @NotNull(message = "Total money spent is required")
    private double moneySpent;

}
