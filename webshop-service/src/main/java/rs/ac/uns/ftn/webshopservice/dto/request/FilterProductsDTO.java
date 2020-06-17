package rs.ac.uns.ftn.webshopservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterProductsDTO {

    private String name;
    private String categoryName;
    private Double downPrice;
    private Double topPrice;
    private Boolean isFreeShipping;
}
