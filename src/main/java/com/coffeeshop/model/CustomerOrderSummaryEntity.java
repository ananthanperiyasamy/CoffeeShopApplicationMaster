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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CustomerOrderSummary")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderSummaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  	orderId;
	
	@Column(nullable = false)
	private Double 	subTotalPrice;
	
	@Column(nullable = true)
	private Double 	discount;
	
	@Column(nullable = false)
	private Double 	totalPrice;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date 	orderCreatedDate;
	
	//if require can add customer details also in this table (skipping it now since not getting that info now)

}
