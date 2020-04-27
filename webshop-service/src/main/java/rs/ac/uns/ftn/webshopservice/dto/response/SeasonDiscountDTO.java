package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;

@Getter
@Setter
@NoArgsConstructor
public class SeasonDiscountDTO {

    private Long id;
    private String from;
    private String to;
    private String name;

    public SeasonDiscountDTO(SeasonDiscount sd) {
        this.id = sd.getId();
        this.from = sd.getDateFrom().toString();
        this.to = sd.getDateTo().toString();
        this.name = sd.getName();
    }
}
