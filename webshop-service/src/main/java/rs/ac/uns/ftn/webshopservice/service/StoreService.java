package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.AddStoreDTO;
import rs.ac.uns.ftn.webshopservice.model.Store;

import java.util.List;

public interface StoreService {

    List<Store> getAll();
    Store addStore(AddStoreDTO storeDto);
}
