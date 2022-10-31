package com.coffeeshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeeshop.model.ProductsEntity;

@Repository
public interface CoffeeRepo extends JpaRepository<ProductsEntity, Long> {

	
}
