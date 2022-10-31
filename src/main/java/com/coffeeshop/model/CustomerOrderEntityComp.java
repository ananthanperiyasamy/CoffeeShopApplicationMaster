package com.coffeeshop.model;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderEntityComp implements Serializable{

	private static final long serialVersionUID = -8598039376477244969L;

	private Long  	orderId;
	
	private Long	productId;

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerOrderEntityComp other = (CustomerOrderEntityComp) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId);
	}
}
