package rs.ac.uns.ftn.webshopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.webshopservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
