package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.AddStoreDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.EditStoreDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.model.Owner;
import rs.ac.uns.ftn.webshopservice.model.Product;
import rs.ac.uns.ftn.webshopservice.model.Store;
import rs.ac.uns.ftn.webshopservice.model.User;
import rs.ac.uns.ftn.webshopservice.repository.ProductRepository;
import rs.ac.uns.ftn.webshopservice.repository.StoreRepository;
import rs.ac.uns.ftn.webshopservice.service.StoreService;
import rs.ac.uns.ftn.webshopservice.service.UserService;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Store findById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store with id=" + id + " doesn't exist"));
    }

    @Override
    public Store getMyStore() {
        Owner owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return owner.getStore();
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store addStore(AddStoreDTO storeDto) {
        if (storeRepository.findByName(storeDto.getName()).isPresent()) {
            throw new ApiRequestException("Store with this name already exists");
        }

        Owner user = (Owner) userService.addOwner(storeDto.getUser());

        Store store = new Store();
        store.setName(storeDto.getName());
        store.setFrequentBuyerDiscount(storeDto.getFrequentBuyerDiscount() / 100);
        store.setOwner(user);

        store = storeRepository.save(store);

        return store;
    }

    @Override
    public void delete(Long id) {
        Store store = this.findById(id);
        userService.delete(store.getOwner().getId());

        List<Product> products = productRepository.findByStoreId(store.getId());
        products.stream().forEach(product -> productRepository.delete(product));
        storeRepository.delete(store);
    }

    @Override
    public Store edit(EditStoreDTO storeDto) {
        Owner owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Store store = owner.getStore();

        if (storeDto.getName() != null && !storeDto.getName().isEmpty()) {
            store.setName(storeDto.getName());
            storeRepository.save(store);
        }

        if (storeDto.getFrequentBuyerDiscount() != null) {
            store.setFrequentBuyerDiscount(storeDto.getFrequentBuyerDiscount() / 100);
            store = storeRepository.save(store);
        }

        return store;
    }
}
