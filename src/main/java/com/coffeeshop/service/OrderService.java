package com.coffeeshop.service;

import com.coffeeshop.model.CustomerOrderSummaryDTO;

public interface OrderService {
	
	/**
	* method to find lowest price from the list of ordered product and apply discount and store it DB
	*
	* @param  customerOrderSummaryDTO  		-  object from com.CoffeeShop.model.ProductDTO
	* @return CustomerOrderSummaryDTO 		-  return object of CustomerOrderSummaryDTO 
	*/
	Object addProductInCart(CustomerOrderSummaryDTO customerOrderDTO);
	
	/**
	* @return ResponseEntity<List<ProductsEntity>> -  list of com.CoffeeShop.model.ProductsEntity object
	*/
	Object getAllProducts();

}
