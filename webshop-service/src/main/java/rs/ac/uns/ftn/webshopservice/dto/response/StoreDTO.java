package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.Store;

@NoArgsConstructor
@Getter
@Setter
public class StoreDTO {

    private Long id;
    private String name;
    private float frequentBuyerDiscount;
    private String ownerName;

    public StoreDTO(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.frequentBuyerDiscount = store.getFrequentBuyerDiscount();
        this.ownerName = store.getOwner().getName();
    }
}
