package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.request.AddStoreDTO;
import rs.ac.uns.ftn.webshopservice.dto.request.EditStoreDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.StoreDTO;
import rs.ac.uns.ftn.webshopservice.mappers.StoreMapper;
import rs.ac.uns.ftn.webshopservice.model.Store;
import rs.ac.uns.ftn.webshopservice.service.StoreService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<StoreDTO>> getAll() {
        List<Store> stores = storeService.getAll();
        return new ResponseEntity<>(StoreMapper.toListDto(stores), HttpStatus.OK);
    }

    @PostMapping("/public")
    public ResponseEntity addStore(@Valid @RequestBody AddStoreDTO storeDto) {
        storeService.addStore(storeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        storeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<StoreDTO> edit(@Valid @RequestBody EditStoreDTO storeDto) {
        Store store = storeService.edit(storeDto);
        return new ResponseEntity<>(StoreMapper.toDto(store), HttpStatus.OK);
    }
}
