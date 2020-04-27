package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.request.AddProductCategoryDiscountDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.ProductCategoryDiscountDTO;
import rs.ac.uns.ftn.webshopservice.mappers.ProductCategoryDiscountMapper;
import rs.ac.uns.ftn.webshopservice.model.ProductCategoryDiscount;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryDiscountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category-discounts")
public class ProductCategoryDiscountController {

    @Autowired
    private ProductCategoryDiscountService discountService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProductCategoryDiscountDTO>> getAll() {
        List<ProductCategoryDiscount> discounts = discountService.getAll();
        return new ResponseEntity<>(ProductCategoryDiscountMapper.toListDto(discounts), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductCategoryDiscountDTO> add(@Valid @RequestBody AddProductCategoryDiscountDTO discount) {
        ProductCategoryDiscount addedDiscount = discountService.add(discount);
        return new ResponseEntity<>(ProductCategoryDiscountMapper.toDto(addedDiscount), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        discountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
