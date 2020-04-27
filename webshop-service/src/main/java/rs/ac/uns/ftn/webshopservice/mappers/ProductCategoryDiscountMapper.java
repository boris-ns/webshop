package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.response.ProductCategoryDiscountDTO;
import rs.ac.uns.ftn.webshopservice.model.ProductCategoryDiscount;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryDiscountMapper {

    public static List<ProductCategoryDiscountDTO> toListDto(List<ProductCategoryDiscount> discounts) {
        return discounts.stream()
                .map(ProductCategoryDiscountDTO::new).collect(Collectors.toList());
    }

    public static ProductCategoryDiscountDTO toDto(ProductCategoryDiscount discount) {
        return new ProductCategoryDiscountDTO(discount);
    }

    private ProductCategoryDiscountMapper() {
    }
}
