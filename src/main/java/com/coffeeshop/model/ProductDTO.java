package com.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
	
	private Long 	productId;
	private String 	productName;
	private int 	quantity;
	private String 	category;
	private Double 	productPrice;
	private Double 	totalProductPrice;
	@JsonIgnore
	private boolean isLowestPrice;
	

}
