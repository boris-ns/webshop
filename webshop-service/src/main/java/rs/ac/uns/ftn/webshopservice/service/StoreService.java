package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.AddStoreDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.EditStoreDTO;
import rs.ac.uns.ftn.webshopservice.model.Store;

import java.util.List;

public interface StoreService {

    Store findById(Long id);
    Store getMyStore();
    List<Store> getAll();
    Store addStore(AddStoreDTO storeDto);
    void delete(Long id);
    Store edit(EditStoreDTO storeDto);
}
