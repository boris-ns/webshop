package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;

@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryDTO {

    private Long id;
    private String name;

    public ProductCategoryDTO(ProductCategory category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
