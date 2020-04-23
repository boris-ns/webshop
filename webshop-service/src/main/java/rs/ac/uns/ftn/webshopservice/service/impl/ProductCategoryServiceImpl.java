package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.AddProductCategoryDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;
import rs.ac.uns.ftn.webshopservice.repository.ProductCategoryRepository;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryService;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product category with id " + id + " doesn't exist."));
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory add(AddProductCategoryDTO category) {
        if (productCategoryRepository.findByName(category.getName()).isPresent()) {
            throw new ApiRequestException("Product category with name " + category.getName() + " already exists.");
        }

        ProductCategory productCategory = new ProductCategory(category.getName());
        productCategory = productCategoryRepository.save(productCategory);
        return productCategory;
    }

    @Override
    public void delete(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product category with id " + id + " doesn't exist."));

        // @TODO: Dodati proveru da li postoji neki artikal koji ima ovu kategoriju
        // ako ima onda ne moze da se brise

        productCategoryRepository.delete(category);
    }
}
