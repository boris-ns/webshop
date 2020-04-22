package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.response.ProductCategoryDTO;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryMapper {

    public static ProductCategoryDTO toDto(ProductCategory category) {
        return new ProductCategoryDTO(category);
    }

    public static List<ProductCategoryDTO> toListDto(List<ProductCategory> categories) {
        return categories.stream()
                .map(ProductCategoryDTO::new).collect(Collectors.toList());
    }

    private ProductCategoryMapper() {
    }
}
