package rs.ac.uns.ftn.webshopservice.service;

import rs.ac.uns.ftn.webshopservice.dto.request.AddSeasonDiscountDTO;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;

import java.util.List;

public interface SeasonDiscountService {

    List<SeasonDiscount> getAll();
    SeasonDiscount findById(Long id);
    SeasonDiscount add(AddSeasonDiscountDTO discount);
    void delete(Long id);
}
