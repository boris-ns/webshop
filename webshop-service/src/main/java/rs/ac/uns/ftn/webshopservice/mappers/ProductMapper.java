package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.request.ProductToAddDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.ProductDTO;
import rs.ac.uns.ftn.webshopservice.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product);
    }

    public static List<ProductDTO> toListDto(List<Product> products) {
        return products.stream()
                .map(ProductDTO::new).collect(Collectors.toList());
    }

    public static Product toProduct(ProductToAddDTO productToAdd) {
        Product product = new Product();
        product.setName(productToAdd.getName());
        product.setPrice(productToAdd.getPrice());
        product.setShippingPrice(productToAdd.getShippingPrice());
        product.setQuantity(productToAdd.getQuantity());
        product.setMaxQuantity(productToAdd.getMaxQuantity());
        product.setMaxOrderQuantity(productToAdd.getMaxOrderQuantity());
        product.setQuantityDiscount(productToAdd.getQuantityDiscount());
        product.setOrderQuantityDiscount(productToAdd.getOrderQuantityDiscount());
        product.setDiscount(productToAdd.getDiscount());
        product.setMaxDiscount(productToAdd.getMaxDiscount());
        product.setCoupon(productToAdd.getCoupon());
        product.setCouponDiscount(productToAdd.getCouponDiscount());
        product.setMaxOrderQuantity(productToAdd.getMaxOrderQuantity());

        return product;
    }

    private ProductMapper() {
    }
}
