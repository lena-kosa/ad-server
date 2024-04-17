package com.ads.adserver.repository;

import com.ads.adserver.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.id, p.title, p.category, p.serial_number, p.price " +
            "FROM (product p JOIN campaign_product cp ON p.id=cp.product_id AND p.category=?1) " +
            "JOIN campaign c ON c.id=cp.campaign_id ORDER BY bid DESC LIMIT 1",
            nativeQuery = true)
    List<Product> findMaxBidByCategory(@Param("category") String category);

}
