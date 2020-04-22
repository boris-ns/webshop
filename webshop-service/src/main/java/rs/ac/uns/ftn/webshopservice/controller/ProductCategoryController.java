package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.request.AddProductCategoryDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.ProductCategoryDTO;
import rs.ac.uns.ftn.webshopservice.mappers.ProductCategoryMapper;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProductCategoryDTO>> getAll() {
        List<ProductCategory> categories = productCategoryService.findAll();
        return new ResponseEntity<>(ProductCategoryMapper.toListDto(categories), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductCategoryDTO> add(@RequestBody AddProductCategoryDTO category) {
        ProductCategory newCategory = productCategoryService.add(category);
        return new ResponseEntity<>(ProductCategoryMapper.toDto(newCategory), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
