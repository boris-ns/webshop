package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.EditProductDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.FilterProductsDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.PlaceOrderDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.ProductToAddDTO;
import rs.ac.uns.ftn.webshopservice.model.Order;
import rs.ac.uns.ftn.webshopservice.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    List<Product> getAllMyProducts();
    Product add(ProductToAddDTO productToAdd);
    Product edit(EditProductDTO productDto);
    void delete(Long id);
    Order buy(PlaceOrderDTO order);
    List<Product> recommendations();
    List<Product> filter(FilterProductsDTO filter);
}
