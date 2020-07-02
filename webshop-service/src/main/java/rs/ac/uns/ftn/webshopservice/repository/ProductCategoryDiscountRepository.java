package rs.ac.uns.ftn.webshopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.webshopservice.model.ProductCategory;
import rs.ac.uns.ftn.webshopservice.model.ProductCategoryDiscount;

import java.util.Date;

@Repository
public interface ProductCategoryDiscountRepository extends JpaRepository<ProductCategoryDiscount, Long> {

    @Query("SELECT pcd FROM ProductCategoryDiscount pcd " +
            "WHERE pcd.dateFrom <= :date AND pcd.dateTo >= :date AND pcd.category.name = :categoryName")
    ProductCategoryDiscount findActive(String categoryName, Date date);
}
