package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.ProductToAddDTO;
import rs.ac.uns.ftn.webshopservice.mappers.ProductMapper;
import rs.ac.uns.ftn.webshopservice.model.*;
import rs.ac.uns.ftn.webshopservice.repository.ProductRepository;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryService;
import rs.ac.uns.ftn.webshopservice.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllMyProducts() {
        Owner user = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return productRepository.findByStoreId(user.getStore().getId());
    }

    @Override
    public Product add(ProductToAddDTO productToAdd) {
        ProductCategory category = productCategoryService.findById(productToAdd.getCategoryId());
        Owner user = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product = ProductMapper.toProduct(productToAdd);
        product.setStore(user.getStore());
        product.setCategory(category);

        product = productRepository.save(product);
        return product;
    }
}
