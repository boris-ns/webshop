package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.ProductToAddDTO;
import rs.ac.uns.ftn.webshopservice.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    List<Product> getAllMyProducts();
    Product add(ProductToAddDTO productToAdd);
    void delete(Long id);
}
