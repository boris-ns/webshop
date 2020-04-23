package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.response.StoreDTO;
import rs.ac.uns.ftn.webshopservice.model.Store;

import java.util.List;
import java.util.stream.Collectors;

public class StoreMapper {

    public static StoreDTO toDto(Store store) {
        return new StoreDTO(store);
    }

    public static List<StoreDTO> toListDto(List<Store> stores) {
        return stores.stream()
                .map(StoreDTO::new).collect(Collectors.toList());
    }

    private StoreMapper() {
    }
}
