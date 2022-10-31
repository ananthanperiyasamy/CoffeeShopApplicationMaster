package com.coffeeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.model.CustomerOrderSummaryDTO;
import com.coffeeshop.service.OrderService;

/**
 * controller to order the product by customer
 * 
 * @author Ananthan Periyasamy
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/view/products")
	public ResponseEntity getAllProducts() {
		return new ResponseEntity<>(orderService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("/addToCart")
	public ResponseEntity addToCart(@RequestBody CustomerOrderSummaryDTO customerOrderDTO){
		return new ResponseEntity<>(orderService.addProductInCart(customerOrderDTO),HttpStatus.OK);
	}

}
