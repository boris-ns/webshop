package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.AddStoreDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.model.Owner;
import rs.ac.uns.ftn.webshopservice.model.Store;
import rs.ac.uns.ftn.webshopservice.model.User;
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
}
