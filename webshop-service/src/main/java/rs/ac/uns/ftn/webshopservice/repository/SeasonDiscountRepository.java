package rs.ac.uns.ftn.webshopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;

@Repository
public interface SeasonDiscountRepository extends JpaRepository<SeasonDiscount, Long> {
}
