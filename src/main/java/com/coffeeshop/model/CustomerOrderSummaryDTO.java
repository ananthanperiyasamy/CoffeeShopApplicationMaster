package com.coffeeshop.model;

import java.util.List;
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
public class CustomerOrderSummaryDTO {
	
	private Long orderId;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ProductDTO> orderRequest;
	
	private Double subTotalPrice;
	
	private Double discount;
	
	private Double totalPrice;

}
