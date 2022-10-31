package com.coffeeshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CustomerOrder")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CustomerOrderEntityComp.class)
public class CustomerOrderEntity {
	
	@Id
	private Long  	orderId;
	
	@Id
	private Long	productId;
	
	@Column(nullable = true, length = 50)      
	private String 	productName;
	
	@Column(nullable = false)
	private int 	quantity;
	
	@Column(nullable = false, length = 50)
	private String 	productCategory;
	
}
