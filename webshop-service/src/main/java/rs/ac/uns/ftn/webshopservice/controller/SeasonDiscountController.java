package rs.ac.uns.ftn.webshopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.request.AddSeasonDiscountDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.SeasonDiscountDTO;
import rs.ac.uns.ftn.webshopservice.mappers.SeasonDiscountMapper;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;
import rs.ac.uns.ftn.webshopservice.service.SeasonDiscountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/season-discounts")
public class SeasonDiscountController {

    @Autowired
    private SeasonDiscountService discountService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SeasonDiscountDTO>> getAll() {
        List<SeasonDiscount> discounts = discountService.getAll();
        return new ResponseEntity<>(SeasonDiscountMapper.toListDto(discounts), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SeasonDiscountDTO> add(@Valid @RequestBody AddSeasonDiscountDTO discount) {
        SeasonDiscount addedDiscount = discountService.add(discount);
        return new ResponseEntity<>(SeasonDiscountMapper.toDto(addedDiscount), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        discountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
