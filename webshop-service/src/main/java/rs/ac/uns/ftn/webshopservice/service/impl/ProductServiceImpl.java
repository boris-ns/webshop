package rs.ac.uns.ftn.webshopservice.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.config.beans.cepsession.CepSession;
import rs.ac.uns.ftn.webshopservice.config.consts.KieAgendaGroups;
import rs.ac.uns.ftn.webshopservice.dto.request.EditProductDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.FilterProductsDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.PlaceOrderDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.ProductToAddDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.mappers.ProductMapper;
import rs.ac.uns.ftn.webshopservice.model.*;
import rs.ac.uns.ftn.webshopservice.model.events.TransactionEvent;
import rs.ac.uns.ftn.webshopservice.repository.OrderRepository;
import rs.ac.uns.ftn.webshopservice.repository.ProductRepository;
import rs.ac.uns.ftn.webshopservice.repository.UserRepository;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryDiscountService;
import rs.ac.uns.ftn.webshopservice.service.ProductCategoryService;
import rs.ac.uns.ftn.webshopservice.service.ProductService;
import rs.ac.uns.ftn.webshopservice.service.SeasonDiscountService;
import rs.ac.uns.ftn.webshopservice.utils.KieSessionCreator;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SeasonDiscountService seasonDiscountService;

    @Autowired
    private ProductCategoryDiscountService categoryDiscountService;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private CepSession cepSession;


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
        product.setNeedsRestock(false);

        product = productRepository.save(product);
        return product;
    }

    @Override
    public Product edit(EditProductDTO productDto) {
        Owner owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Store store = owner.getStore();
        Product product = this.getById(productDto.getId());

        if (!product.getStore().getId().equals(store.getId())) {
            throw new ApiRequestException("You are not allowed edit this products");
        }

        if (productDto.getName() != null && !productDto.getName().isEmpty()) product.setName(productDto.getName());
        if (productDto.getPrice() != null) product.setPrice(productDto.getPrice());
        if (productDto.getShippingPrice() != null) product.setShippingPrice(productDto.getShippingPrice());
        if (productDto.getQuantity() != null) product.setQuantity(productDto.getQuantity());
        if (productDto.getMaxOrderQuantity() != null) product.setMaxOrderQuantity(productDto.getMaxOrderQuantity());
        if (productDto.getQuantityDiscount() != null) product.setQuantityDiscount(productDto.getQuantityDiscount() / 100);
        if (productDto.getOrderQuantityDiscount() != null) product.setOrderQuantityDiscount(productDto.getOrderQuantityDiscount() / 100);
        if (productDto.getDiscount() != null) product.setDiscount(productDto.getDiscount() / 100);
        if (productDto.getMaxDiscount() != null) product.setMaxDiscount(productDto.getMaxDiscount() / 100);
        if (productDto.getCoupon() != null && !productDto.getCoupon().isEmpty()) product.setCoupon(productDto.getCoupon());
        if (productDto.getCouponDiscount() != null) product.setCouponDiscount(productDto.getCouponDiscount() / 100);

        if (product.getQuantity() >= 20) product.setNeedsRestock(false);
        else product.setNeedsRestock(true);

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

        if (order.getQuantity() > product.getQuantity()) {
            throw new ApiRequestException("Not enough products in the store.");
        }

        if (order.getQuantity() > product.getMaxOrderQuantity()) {
            throw new ApiRequestException("You can't order that much products at once");
        }

        Order productOrder = new Order(product, order.getCoupon(), order.getQuantity());

        // Calculate discount for order
        KieSession kieSession = kieContainer.newKieSession("DefaultSession");
        kieSession.insert(productOrder);
        kieSession.insert(buyer);
        kieSession.insert(product);

        SeasonDiscount seasonDiscount = seasonDiscountService.findActive();
        if (seasonDiscount != null)
            kieSession.insert(seasonDiscount);

        ProductCategoryDiscount categoryDiscount = categoryDiscountService.findActive(product.getCategory());
        if (categoryDiscount != null)
            kieSession.insert(categoryDiscount);

        kieSession.getAgenda().getAgendaGroup(KieAgendaGroups.ORDER_DISCOUNTS).setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();

//        double priceDiscount =
//                (productOrder.getPrice() - productOrder.getProduct().getShippingPrice()) * productOrder.getDiscount();
//        productOrder.setPrice(productOrder.getPrice() - priceDiscount);

        productOrder = orderRepository.save(productOrder);
        buyer.getOrders().add(productOrder);

//        product.setQuantity(product.getQuantity() - productOrder.getQuantity());
        productRepository.save(product);

        // Classify buyers
        KieSession kieClassifyBuyers = KieSessionCreator.classifyBuyersSession;

        // If session created from templates doesn't exist, use the default one
        if (kieClassifyBuyers == null) {
            kieClassifyBuyers = kieContainer.newKieSession("DefaultSession");
        }

        kieClassifyBuyers.insert(buyer);
        kieClassifyBuyers.getAgenda().getAgendaGroup(KieAgendaGroups.CLASSIFY_BUYERS).setFocus();
        kieClassifyBuyers.fireAllRules();
        kieClassifyBuyers.dispose();

        userRepository.save(buyer);

        TransactionEvent event = new TransactionEvent(buyer.getId(), productOrder.getPrice());
        KieSession kieTransactionSession = cepSession.getCepSession();
        cepSession.getEvents().add(event);
        kieTransactionSession.insert(event);
        int fired = kieTransactionSession.fireAllRules();

        return productOrder;
    }

    @Override
    public List<Product> recommendations() {
        Buyer buyer = (Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> products = this.getAll();
        ArrayList<Product> recommendations = new ArrayList<>();

        KieSession kieSession = kieContainer.newKieSession("DefaultSession");
        kieSession.setGlobal("g_recommendations", recommendations);
        kieSession.setGlobal("g_products", products);
        kieSession.insert(buyer);
        kieSession.getAgenda().getAgendaGroup(KieAgendaGroups.RECOMMENDATIONS).setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();

        return recommendations;
    }

    @Override
    public List<Product> filter(FilterProductsDTO filter) {
        // @HACK @NOTE: Za free shipping slati null ili true, nemoj false iz nekog razloga onda ne radi
        if (filter.getIsFreeShipping() != null && !filter.getIsFreeShipping()) {
            filter.setIsFreeShipping(null);
        }

        return productRepository.filter(
                filter.getName(), filter.getCategoryName(), filter.getDownPrice(),
                filter.getTopPrice(), filter.getIsFreeShipping()
        );
    }
}
