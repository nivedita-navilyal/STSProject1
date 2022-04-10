package com.cgg.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.vo.Product;

@Repository
public interface ProductRepository extends JpaRepository<ProductDto,Integer> {

}
