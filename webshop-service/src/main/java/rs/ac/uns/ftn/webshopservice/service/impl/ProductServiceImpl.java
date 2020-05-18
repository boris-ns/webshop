package rs.ac.uns.ftn.webshopservice.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.PlaceOrderDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.ProductToAddDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.mappers.ProductMapper;
import rs.ac.uns.ftn.webshopservice.model.*;
import rs.ac.uns.ftn.webshopservice.repository.OrderRepository;
import rs.ac.uns.ftn.webshopservice.repository.ProductRepository;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryService;
import rs.ac.uns.ftn.webshopservice.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private KieContainer kieContainer;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " doesn't exist"));
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

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " doesn't exist"));

        Owner user = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!product.getStore().getId().equals(user.getStore().getId())) {
            throw new ApiRequestException("You can't delete product that isn't in your store.");
        }

        productRepository.delete(product);
    }

    @Override
    public Order buy(PlaceOrderDTO order) {
        Buyer buyer = (Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = this.getById(order.getProductId());
        Order productOrder = new Order(product, order.getQuantity());

        KieSession kieSession = kieContainer.newKieSession();
//        kieSession.setGlobal("order", productOrder);
        kieSession.insert(productOrder);
        kieSession.insert(buyer);
        kieSession.fireAllRules();
//        Order newProductOrder = (Order) kieSession.getGlobal("order");
        kieSession.dispose();

        double priceDiscount =
                (productOrder.getPrice() - productOrder.getProduct().getShippingPrice()) * productOrder.getDiscount();
        productOrder.setPrice(productOrder.getPrice() - priceDiscount);
        productOrder = orderRepository.save(productOrder);

        return productOrder;
    }
}
