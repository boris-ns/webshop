package rs.ac.uns.ftn.webshopservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.webshopservice.dto.request.AddSeasonDiscountDTO;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ApiRequestException;
import rs.ac.uns.ftn.webshopservice.exception.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;
import rs.ac.uns.ftn.webshopservice.repository.SeasonDiscountRepository;
import rs.ac.uns.ftn.webshopservice.service.SeasonDiscountService;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class SeasonDiscountServiceImpl implements SeasonDiscountService {

    @Autowired
    private SeasonDiscountRepository discountRepository;

    @Override
    public List<SeasonDiscount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public SeasonDiscount findById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Season discount with id " + id + " doesn't exist"));
    }

    @Override
    public SeasonDiscount findActive() {
        return discountRepository.findActive(new Date());
    }

    @Override
    public SeasonDiscount add(AddSeasonDiscountDTO discount) {
        SeasonDiscount newDiscount = new SeasonDiscount();
        newDiscount.setName(discount.getName());
        newDiscount.setDateFrom(new GregorianCalendar(discount.getFrom()[2], discount.getFrom()[1] - 1, discount.getFrom()[0]).getTime());
        newDiscount.setDateTo(new GregorianCalendar(discount.getTo()[2], discount.getTo()[1] - 1, discount.getTo()[0]).getTime());
        newDiscount.setDiscount(discount.getDiscount() / 100);

        if (newDiscount.getDateFrom().after(newDiscount.getDateTo())) {
            throw new ApiRequestException("Start date can't be after end date.");
        }

        newDiscount = discountRepository.save(newDiscount);
        return newDiscount;
    }

    @Override
    public void delete(Long id) {
        SeasonDiscount discount = this.findById(id);
        discountRepository.delete(discount);
    }
}
