package rs.ac.uns.ftn.webshopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.webshopservice.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(Long id);
    List<Product> findByCategoryId(Long id);

    @Query("SELECT p FROM Product p " +
            "WHERE (:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name,'%'))) AND " +
            "(:categoryName IS NULL OR lower(p.category.name) LIKE lower(concat('%', :categoryName, '%'))) AND " +
            "(:downPrice IS NULL OR p.price >= :downPrice) AND " +
            "(:upPrice IS NULL OR p.price <= :upPrice) AND " +
            "(:freeShipping IS NULL OR (:freeShipping = TRUE AND p.shippingPrice = 0))")
    List<Product> filter(String name, String categoryName, Float downPrice, Float upPrice, Boolean freeShipping);
}
