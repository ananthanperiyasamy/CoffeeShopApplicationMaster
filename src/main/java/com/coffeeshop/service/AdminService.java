package com.coffeeshop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.coffeeshop.model.ProductDTO;

public interface AdminService {

	/**
	* @param  productsList  			-  list of object from com.CoffeeShop.model.ProductDTO
	* @return String				  	-  the success message in string
	*/
	String addProduct(List<ProductDTO> productList);

	/**
	* @param  productsList  			-  list of object from com.CoffeeShop.model.ProductDTO
	* @return String 					-  success  message in string
	*/
	String updateProducts(List<ProductDTO> productsList);

	/**
	* @param  productId  				-  product id which need to be deleted
	* @return String				  	-  the success message in string
	*/
	String deleteProductById(Long productId);

	/**
	* @param  category  				-  category of products which admin want to see
	* @return String  					-  the product category(top product by category) 
	*/
	String getMostUsedCategory(String category);
	
}
