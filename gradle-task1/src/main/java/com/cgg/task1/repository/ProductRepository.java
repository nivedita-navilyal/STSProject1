package com.cgg.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgg.task1.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
