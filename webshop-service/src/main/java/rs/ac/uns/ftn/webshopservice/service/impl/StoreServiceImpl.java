package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.model.Store;
import rs.ac.uns.ftn.webshopservice.repository.StoreRepository;
import rs.ac.uns.ftn.webshopservice.service.StoreService;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }
}
