package com.coffeeshop.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeeshop.constant.CoffeeShopConstant;
import com.coffeeshop.exception.CoffeeShopCustomException;
import com.coffeeshop.model.ProductDTO;
import com.coffeeshop.repo.CoffeeRepo;
import com.coffeeshop.repo.CustomerOrderRepo;
import com.coffeeshop.service.mapper.EntityMapper;

/**
 *  admin service class
 * 
 * @author Ananthan Periyasamy 
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger LOGGER = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	private CoffeeRepo coffeeRepo;
	
	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	@Autowired
	private EntityMapper entityMapper; 

	
	/**
	* @param  productsList  			-  list of object from com.CoffeeShop.model.ProductDTO
	* @return String				  	-  the success message in string
	*/
	public String addProduct(List<ProductDTO> productsList) {
		try {
			coffeeRepo.saveAll(entityMapper.mapProductDTOToEntity(productsList));
			return CoffeeShopConstant.PRODUCT_ADDED_SUCCESS_MESSAGE;
		}
		catch(Exception exception) { 
			LOGGER.error(CoffeeShopConstant.PRODUCT_ADDED_FAILED_MESSAGE + exception.getMessage());
			throw new CoffeeShopCustomException(exception.getMessage());
		}
	}

	
	/**
	* @param  productsList  			-  list of object from com.CoffeeShop.model.ProductDTO
	* @return String 					-  success  message in string
	*/
	public String updateProducts(List<ProductDTO> productsList) {
		try {
			coffeeRepo.saveAll(entityMapper.mapProductDTOToEntity(productsList));
			return CoffeeShopConstant.PRODUCT_UPDATED_SUCCESS_MESSAGE;
		}
		catch(Exception|Error exception) {
			LOGGER.error(CoffeeShopConstant.PRODUCT_UPDATED_FAILED_MESSAGE + exception.getMessage());
			throw new CoffeeShopCustomException(exception.getMessage());
		}
	}
	 
	
	/**
	* @param  productId  				-  product id which need to be deleted
	* @return String				  	-  the success message in string
	*/
	public String deleteProductById(Long productId) {
		try {
			coffeeRepo.deleteById(productId); 
			return CoffeeShopConstant.PRODUCT_DELETED_SUCCESS_MESSAGE;
		}
		catch(Exception exception) { 
			LOGGER.error(CoffeeShopConstant.PRODUCT_DELETED_FAILED_MESSAGE + productId + exception.getMessage());
			throw new CoffeeShopCustomException(CoffeeShopConstant.PRODUCT_DELETED_FAILED_MESSAGE + productId + exception.getMessage());
		}
	}

	
	/**
	* @param  category  				-  category of products which admin want to see
	* @return String  					-  the product category(top product by category) 
	*/
	public String getMostUsedCategory(String category) {
		try {
			if(CoffeeShopConstant.TOPPINGS_CONSTANT.equalsIgnoreCase(category)) {
				return (String) customerOrderRepo.findTopByProductCategory(category);
			}
			else {
				// extend this method to find most used other category
				throw new CoffeeShopCustomException(CoffeeShopConstant.YET_TO_HANDLE_THIS_SCANERIOS + category);
			}
		}
		catch(Exception exception) { 
			LOGGER.error(exception.getMessage());
			throw new CoffeeShopCustomException(exception.getMessage());
		}
	}
	
}
