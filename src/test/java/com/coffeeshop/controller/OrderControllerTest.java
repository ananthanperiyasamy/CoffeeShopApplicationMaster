package com.coffeeshop.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.coffeeshop.controller.OrderController;
import com.coffeeshop.model.CustomerOrderSummaryDTO;
import com.coffeeshop.model.ProductDTO;
import com.coffeeshop.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	
	@MockBean
	private OrderService orderService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(roles = { "USER","ADMIN" })
	void shouldGetAllProducts() throws Exception {
		
		List<ProductDTO> productList = createProductListData();
		
		when(orderService.getAllProducts()).thenReturn(productList);
		
		mockMvc.perform(get("/order/view/products"))
				.andExpect(status().isOk())
			    .andExpect(jsonPath("$.size()").value(productList.size()))
			    .andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "USER","ADMIN" })
	void shouldGetNoDiscountForOrder() throws Exception {
		
		CustomerOrderSummaryDTO customerOrderSummary = createOrderData();
		// removing the item with high price to make the total amount less than 12
		customerOrderSummary.getOrderRequest().remove(2); 
		//removing the item to make order list contain less than 3 items
		customerOrderSummary.getOrderRequest().remove(2);
		when(orderService.addProductInCart(customerOrderSummary)).thenReturn(customerOrderSummary);
		
		mockMvc.perform(get("/order/addToCart")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(customerOrderSummary)))
				.andExpect(status().isOk())
			    .andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "USER","ADMIN" })
	void shouldGetFirstDiscountForOrder() throws Exception {
		
		CustomerOrderSummaryDTO customerOrderSummary = createOrderData();
		// removing the item with high price to make the total amount less than 12
		customerOrderSummary.getOrderRequest().remove(2); 
		when(orderService.addProductInCart(customerOrderSummary)).thenReturn(customerOrderSummary);
		
		mockMvc.perform(get("/order/addToCart")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(customerOrderSummary)))
				.andExpect(status().isOk())
			    .andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "USER","ADMIN" })
	void shouldGetSecondDiscountForOrder() throws Exception {
		
		CustomerOrderSummaryDTO customerOrderSummary = createOrderData();
		//removing the item to make order list contain less than 3 items
		customerOrderSummary.getOrderRequest().remove(2);
		when(orderService.addProductInCart(customerOrderSummary)).thenReturn(customerOrderSummary);
		
		mockMvc.perform(get("/order/addToCart")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(customerOrderSummary)))
				.andExpect(status().isOk())
			    .andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "USER","ADMIN" })
	void shouldBeEligibleForBothDiscount() throws Exception {
		
		CustomerOrderSummaryDTO customerOrderSummary = createOrderData();
		
		when(orderService.addProductInCart(customerOrderSummary)).thenReturn(customerOrderSummary);
		
		mockMvc.perform(get("/order/addToCart")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(customerOrderSummary)))
				.andExpect(status().isOk())
			    .andDo(print());
	}
	
	
	private CustomerOrderSummaryDTO createOrderData() {
		CustomerOrderSummaryDTO customerOrderSummaryDTO = new CustomerOrderSummaryDTO();
		customerOrderSummaryDTO.setOrderRequest(createProductListData());
		return customerOrderSummaryDTO;
	}

	private List<ProductDTO> createProductListData() {
		return new ArrayList<>(
			      Arrays.asList(
			    		  new ProductDTO(1L,"Tea",1,"Drinks",3.0,0.0,false),
			    		  new ProductDTO(2L,"Milk",1,"Drinks",4.0,0.0,false),
			    		  new ProductDTO(3L,"Sugar",1,"Toppings",6.0,0.0,false),
			    		  new ProductDTO(4L,"Lemon",1,"Toppings",3.0,0.0,false)
			      ));
	}

}
