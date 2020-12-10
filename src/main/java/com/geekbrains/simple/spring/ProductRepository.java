package com.geekbrains.simple.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p where p.price > ?1")
    List<Product> findByPriceGreaterThan(int price);

    @Modifying
    @Query(value = "update Product p set p.title = ?1 where p.id = ?2")
    void updateTitleById(String title, long id);
}
