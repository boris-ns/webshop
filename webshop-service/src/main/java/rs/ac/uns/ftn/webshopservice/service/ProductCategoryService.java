package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.AddProductCategoryDTO;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findById(Long id);
    List<ProductCategory> findAll();
    ProductCategory add(AddProductCategoryDTO category);
    void delete(Long id);
}
