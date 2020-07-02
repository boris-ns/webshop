package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.AddProductCategoryDiscountDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;
import rs.ac.uns.ftn.webshopservice.model.ProductCategoryDiscount;
import rs.ac.uns.ftn.webshopservice.repository.ProductCategoryDiscountRepository;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryDiscountService;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryService;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ProductCategoryDiscountServiceImpl implements ProductCategoryDiscountService {

    @Autowired
    private ProductCategoryDiscountRepository discountRepository;

    @Autowired
    private ProductCategoryService categoryService;

    @Override
    public List<ProductCategoryDiscount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public ProductCategoryDiscount findById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category discount with id " + id + " doesn't exist"));
    }

    @Override
    public ProductCategoryDiscount findActive(ProductCategory category) {
        return discountRepository.findActive(category.getName(), new Date());
    }

    @Override
    public ProductCategoryDiscount add(AddProductCategoryDiscountDTO discount) {
        ProductCategory category = categoryService.findById(discount.getCategoryId());

        ProductCategoryDiscount newDiscount = new ProductCategoryDiscount();
        newDiscount.setDateFrom(new GregorianCalendar(discount.getFrom()[2], discount.getFrom()[1] - 1, discount.getFrom()[0]).getTime());
        newDiscount.setDateTo(new GregorianCalendar(discount.getTo()[2], discount.getTo()[1] - 1, discount.getTo()[0]).getTime());
        newDiscount.setCategory(category);
        newDiscount.setDiscount(discount.getDiscount() / 100);

        if (newDiscount.getDateFrom().after(newDiscount.getDateTo())) {
            throw new ApiRequestException("Start date can't be after end date.");
        }

        newDiscount = discountRepository.save(newDiscount);
        return newDiscount;
    }

    @Override
    public void delete(Long id) {
        ProductCategoryDiscount discout = this.findById(id);
        discountRepository.delete(discout);
    }
}
