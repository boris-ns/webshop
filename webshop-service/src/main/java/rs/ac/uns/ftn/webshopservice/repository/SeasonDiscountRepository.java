package rs.ac.uns.ftn.webshopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.webshopservice.model.SeasonDiscount;

import java.util.Date;

@Repository
public interface SeasonDiscountRepository extends JpaRepository<SeasonDiscount, Long> {

    @Query("SELECT sd FROM SeasonDiscount sd " +
            "WHERE sd.dateFrom <= :date AND sd.dateTo >= :date")
    SeasonDiscount findActive(Date date);
}
