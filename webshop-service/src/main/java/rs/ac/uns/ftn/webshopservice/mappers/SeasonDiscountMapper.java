package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.response.SeasonDiscountDTO;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;

import java.util.List;
import java.util.stream.Collectors;

public class SeasonDiscountMapper {

    public static List<SeasonDiscountDTO> toListDto(List<SeasonDiscount> discounts) {
        return discounts.stream()
                .map(SeasonDiscountDTO::new).collect(Collectors.toList());
    }

    public static SeasonDiscountDTO toDto(SeasonDiscount discount) {
        return new SeasonDiscountDTO(discount);
    }

    private SeasonDiscountMapper() {
    }
}
