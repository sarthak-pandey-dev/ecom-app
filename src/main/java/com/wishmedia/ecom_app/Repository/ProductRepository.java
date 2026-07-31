package com.wishmedia.ecom_app.Repository;


import com.wishmedia.ecom_app.dto.ProductResponse;
import com.wishmedia.ecom_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    @Query ("SELECT p FROM products p WHERE p.active=true AND p.stockQuantity>0 and Lower(p.name) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Product> searchProducts(@Param("keyword")String keyword);
}
