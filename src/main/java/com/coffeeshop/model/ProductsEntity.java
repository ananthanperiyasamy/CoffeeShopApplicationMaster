package com.coffeeshop.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class ProductsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(nullable = false, length = 50, unique = true)
	private String productName;

	@Column(nullable = false, unique = false)
	private Double productPrice;

	@Column(nullable = false, length = 50, unique = false)
	private String productCategory;
	
	//not using this added/modified details in our functionality now
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@JsonIgnore
	private Date 	productAddedDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	@JsonIgnore
	private Date 	productModifyDate;

}
