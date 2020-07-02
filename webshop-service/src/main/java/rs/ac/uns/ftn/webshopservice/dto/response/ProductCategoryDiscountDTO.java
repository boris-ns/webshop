package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.ProductCategoryDiscount;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryDiscountDTO {

    private Long id;
    private String from;
    private String to;
    private ProductCategoryDTO category;
    private Float discount;

    public ProductCategoryDiscountDTO(ProductCategoryDiscount discount) {
        this.id = discount.getId();
        this.from = discount.getDateFrom().toString();
        this.to = discount.getDateTo().toString();
        this.category = new ProductCategoryDTO(discount.getCategory());
        this.discount = discount.getDiscount();
    }
}
