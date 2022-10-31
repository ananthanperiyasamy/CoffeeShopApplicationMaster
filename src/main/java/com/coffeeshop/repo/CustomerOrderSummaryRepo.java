package com.coffeeshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffeeshop.model.CustomerOrderSummaryEntity;

public interface CustomerOrderSummaryRepo extends JpaRepository<CustomerOrderSummaryEntity, Long>{

}
