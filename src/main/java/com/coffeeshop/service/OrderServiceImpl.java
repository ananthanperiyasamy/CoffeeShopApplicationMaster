package com.coffeeshop.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coffeeshop.constant.CoffeeShopConstant;
import com.coffeeshop.exception.CoffeeShopCustomException;
import com.coffeeshop.model.CustomerOrderSummaryDTO;
import com.coffeeshop.model.ProductDTO;
import com.coffeeshop.model.ProductsEntity;
import com.coffeeshop.repo.CoffeeRepo;
import com.coffeeshop.repo.CustomerOrderRepo;
import com.coffeeshop.repo.CustomerOrderSummaryRepo;
import com.coffeeshop.service.mapper.EntityMapper;

/**
 *  order service class
 * 
 * @author Ananthan Periyasamy
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

	@Value("${discountPercent}")
	private Double discountPercent;
	@Value("${thresholdPrice}")
	private Double thresholdPrice;
	@Value("${thresholdDrinks}")
	private int thresholdDrinks;

	@Autowired
	private CoffeeRepo coffeeRepo;

	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	@Autowired
	private CustomerOrderSummaryRepo customerOrderSummaryRepo;

	@Autowired
	private EntityMapper entityMapper;

	/**
	* method to get list of all available product from DB
	*
	* @return ProductsEntity 						-  list of object from com.CoffeeShop.model.ProductsEntity
	*/
	@Override
	public List<ProductsEntity> getAllProducts() {
		try {
			return coffeeRepo.findAll();
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			throw new CoffeeShopCustomException(exception.getMessage());
		}
	}

	/**
	* method to find lowest price from the list of ordered product and apply discount and store it DB
	*
	* @param  customerOrderSummaryDTO  		-  object from com.CoffeeShop.model.ProductDTO
	* @return CustomerOrderSummaryDTO 		-  return object of CustomerOrderSummaryDTO 
	*/
	@Override
	public CustomerOrderSummaryDTO addProductInCart(CustomerOrderSummaryDTO customerOrderSummaryDTO) { 

		try {
			Double totalPrice	= 0.0; 
			int drinksCount 	= 0;
			Double lowestPrice 	= 0.0;
			for (ProductDTO productDTO : customerOrderSummaryDTO.getOrderRequest()) {
				// get product details like price and category from db since user entered data have only product id and quantity
				// can improve this by implementing CacheManager 
				Optional<ProductsEntity> productsEntity = coffeeRepo.findById(productDTO.getProductId());
				if (!productsEntity.isPresent()) {
					throw new CoffeeShopCustomException(CoffeeShopConstant.INVALID_PRODUCT_CODE_MESSAGE + productDTO.getProductId());
				}
				ProductsEntity currentProduct = productsEntity.get();
				//get the total count of drinks from ordered product
				if (CoffeeShopConstant.DRINKS_CONSTANT.equalsIgnoreCase(currentProduct.getProductCategory()))
					drinksCount++;
				//Find the lowest price from the ordered product
				if (lowestPrice < currentProduct.getProductPrice()) {
					if (lowestPrice == 0)
						lowestPrice = currentProduct.getProductPrice();
					productDTO.setLowestPrice(false); 
				} else {
					lowestPrice = currentProduct.getProductPrice();
					productDTO.setLowestPrice(true);
				}
				productDTO.setProductPrice(currentProduct.getProductPrice());
				productDTO.setTotalProductPrice(productDTO.getQuantity() * productDTO.getProductPrice());
				productDTO.setCategory(currentProduct.getProductCategory());
				//accumulating the price of all ordered products
				totalPrice = totalPrice + productDTO.getTotalProductPrice();
					
			}
			customerOrderSummaryDTO.setSubTotalPrice(totalPrice); 
			checkAndApplyDiscount(totalPrice, drinksCount, lowestPrice,customerOrderSummaryDTO);
			
			//create order summary for the customer order and store it customerOrderSummaryTable 
			customerOrderSummaryDTO.setOrderId(customerOrderSummaryRepo.save(entityMapper.mapCustomerSummaryDTOToEntity(customerOrderSummaryDTO)).getOrderId());
			
			//create customer ordered items in customer order table to statistics report if require
			customerOrderRepo.saveAll(entityMapper.mapProductDTOToEntity(customerOrderSummaryDTO.getOrderRequest(),customerOrderSummaryDTO.getOrderId()));
			
		}
		catch (Exception exception) { 
			LOGGER.error("Exception occurred while adding product to cart " +  exception);
			throw new CoffeeShopCustomException(exception.getMessage());
		}
		return customerOrderSummaryDTO;
	}

	/**
	* method will check the eligible discount and apply it
	*
	* @param  totalPrice  		-  total amount of ordered products
	* @param  drinksCount  		-  number of drinks in ordered products
	* @param  lowestPrice  		-  lowest price from ordered list
	* @param  customerOrderDTO  -  object from com.CoffeeShop.model.CustomerOrderSummaryDTO
	*/
	private void checkAndApplyDiscount(Double totalPrice, int drinksCount, Double lowestPrice, CustomerOrderSummaryDTO customerOrderDTO) {
		// Discount 1 - if ordered amount is more than 12 && total item is less than 3 => 25% discount on total price
		// Discount 2 - if ordered amount is less than 12 && total item is more than or equal to 3 => discounted the item with lowest price
		// Discount 3 - if discount 1 && discount 2 applied => discounted the item with lowest price
		if ((totalPrice > thresholdPrice && drinksCount >= thresholdDrinks) || drinksCount >= thresholdDrinks) {
			customerOrderDTO.setDiscount(lowestPrice);
			customerOrderDTO.setTotalPrice(totalPrice - lowestPrice);
		} else if (totalPrice > thresholdPrice) {
			customerOrderDTO.setDiscount((totalPrice * discountPercent) / 100);
			customerOrderDTO.setTotalPrice(totalPrice - ((totalPrice * discountPercent) / 100));
		}
		else {
			customerOrderDTO.setTotalPrice(totalPrice);
		}
		customerOrderDTO.setSubTotalPrice(totalPrice);
	}

}
