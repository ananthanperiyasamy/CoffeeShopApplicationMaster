package com.coffeeshop.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.coffeeshop.model.CustomerOrderEntity;
import com.coffeeshop.model.CustomerOrderSummaryDTO;
import com.coffeeshop.model.CustomerOrderSummaryEntity;
import com.coffeeshop.model.ProductDTO;
import com.coffeeshop.model.ProductsEntity;

/**
 * mapper class
 * 
 * @author Ananthan Periyasamy
 */
@Component
public class EntityMapper {
	
	/**
	* method to convert ProductDTO object to CustomerOrderEntity
	*
	* @param  productDTOList  		-  object from com.CoffeeShop.model.ProductDTO
	* @return customerOrderEntity 	-  object from com.CoffeeShop.model.CustomerOrderEntity
	*/
	public CustomerOrderEntity mapCustomerDTOToEntity(ProductDTO productDTO,Long orderId) {
		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		customerOrderEntity.setOrderId(orderId);
		customerOrderEntity.setProductId(productDTO.getProductId());
		customerOrderEntity.setProductName(productDTO.getProductName());
		customerOrderEntity.setQuantity(productDTO.getQuantity());
		customerOrderEntity.setProductCategory(productDTO.getCategory());
		return customerOrderEntity;
	}
 
	/**
	* method to convert list of ProductDTO object to list of CustomerOrderEntity
	*
	* @param  productDTOList  		-  list of object from com.CoffeeShop.model.ProductDTO
	* @return customerOrderEntity 	-  list of object from com.CoffeeShop.model.CustomerOrderEntity
	*/
	public List<CustomerOrderEntity> mapProductDTOToEntity(List<ProductDTO> productDTOList, Long orderId) {
		return productDTOList.stream().map(data -> mapCustomerDTOToEntity(data,orderId)).collect(Collectors.toList());
		
	}

	/**
	* method to convert CustomerOrderSummaryDTO object to CustomerOrderSummaryEntity
	*
	* @param  customerOrderSummaryDTO  		-  object of com.CoffeeShop.model.CustomerOrderSummaryDTO
	* @return customerOrderSummaryEntity 	-  object of com.CoffeeShop.model.CustomerOrderSummaryEntity
	*/
	public CustomerOrderSummaryEntity mapCustomerSummaryDTOToEntity(CustomerOrderSummaryDTO customerOrderSummaryDTO) {
		CustomerOrderSummaryEntity customerOrderSummaryEntity = new CustomerOrderSummaryEntity();
		//if order id already present then use same
		if(customerOrderSummaryDTO.getOrderId() != null)
			customerOrderSummaryEntity.setOrderId(customerOrderSummaryDTO.getOrderId());
		customerOrderSummaryEntity.setDiscount(customerOrderSummaryDTO.getDiscount());
		customerOrderSummaryEntity.setSubTotalPrice(customerOrderSummaryDTO.getSubTotalPrice());
		customerOrderSummaryEntity.setTotalPrice(customerOrderSummaryDTO.getTotalPrice());
		return customerOrderSummaryEntity;
	}

	/**
	* method to convert ProductDTO object to ProductsEntity
	*
	* @param  productList  			-  object of com.CoffeeShop.model.ProductDTO
	* @return productEntityList 	-  object of com.CoffeeShop.model.ProductsEntity
	*/
	public List<ProductsEntity> mapProductDTOToEntity(List<ProductDTO> productList) {
		List<ProductsEntity> productEntityList = new ArrayList<>();
		if (productList != null) {
			for (ProductDTO product : productList) {
				ProductsEntity productEntity = new ProductsEntity();
				//if product id already present then use the same for update
				if(product.getProductId() != null)
					productEntity.setProductId(product.getProductId() );
				productEntity.setProductCategory(product.getCategory());
				productEntity.setProductName(product.getProductName());
				productEntity.setProductPrice(product.getProductPrice());
				productEntityList.add(productEntity);
			}
		}
		return productEntityList;
	}

}
