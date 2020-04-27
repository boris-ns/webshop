package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.AddProductCategoryDiscountDTO;
import rs.ac.uns.ftn.webshopservice.model.ProductCategoryDiscount;

import java.util.List;

public interface ProductCategoryDiscountService {

    List<ProductCategoryDiscount> getAll();
    ProductCategoryDiscount findById(Long id);
    ProductCategoryDiscount add(AddProductCategoryDiscountDTO discount);
    void delete(Long id);
}
