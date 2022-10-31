package com.coffeeshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coffeeshop.model.CustomerOrderEntity;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrderEntity, Long> {

	@Query(value ="select product_name from public.customer_order co\n"
			+ "where co.product_category = :category \n"
			+ "group by product_category,product_name \n"
			+ "order by \n"
			+ "product_category,product_name desc limit 1",nativeQuery = true)
	Object findTopByProductCategory(String category);
	
	

}
