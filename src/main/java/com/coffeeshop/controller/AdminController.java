package com.coffeeshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.model.ProductDTO;
import com.coffeeshop.service.AdminService;


/**
 * controller to add/update/delete the product from database
 * 
 * @author Ananthan Periyasamy
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/addproducts")
	public ResponseEntity<String> addProducts(@RequestBody List<ProductDTO> productsList) {
		return new ResponseEntity<>(adminService.addProduct(productsList),HttpStatus.CREATED);
	}

	@PutMapping("/updateproducts")
	public ResponseEntity<String> updateProducts(@RequestBody List<ProductDTO> productsList) {
		return new ResponseEntity<>(adminService.updateProducts(productsList),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> updateProducts(@PathVariable(required = true) Long id) {
		return new ResponseEntity<>(adminService.deleteProductById(id),HttpStatus.OK);
	}
	
	@GetMapping("/mostused/{category}")
	public ResponseEntity<Object> getMostUsedCategory(@PathVariable(required = true) String category) {
		return new ResponseEntity<>(adminService.getMostUsedCategory(category),HttpStatus.OK);
	}

	
}
