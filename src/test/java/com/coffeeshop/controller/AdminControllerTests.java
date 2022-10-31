package com.coffeeshop.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.springframework.security.test.context.support.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.coffeeshop.constant.CoffeeShopConstant;
import com.coffeeshop.controller.AdminController;
import com.coffeeshop.model.ProductDTO;
import com.coffeeshop.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminController.class)
public class AdminControllerTests {

	@MockBean
	private AdminService adminService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	void shouldAddProduct() throws Exception {
		ProductDTO productDTO = new ProductDTO(1L,"Tea",1,"Drinks",1.0,0.0,false);
		
		mockMvc.perform(post("/admin/addproducts").contentType(MediaType.APPLICATION_JSON)
			      .content(objectMapper.writeValueAsString(Arrays.asList(productDTO))))
			      .andExpect(status().isCreated())
			      .andDo(print());
	}
	
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	void shouldUpdateProduct() throws Exception {
		ProductDTO productDTO = new ProductDTO(1L,"Tea",1,"Drinks",2.0,0.0,false);
		
		when(adminService.addProduct(Arrays.asList(productDTO))).thenReturn(CoffeeShopConstant.PRODUCT_UPDATED_SUCCESS_MESSAGE);
		
		mockMvc.perform(put("/admin/updateproducts").contentType(MediaType.APPLICATION_JSON)
			      .content(objectMapper.writeValueAsString(Arrays.asList(productDTO))))
			      .andExpect(status().isOk())
			      .andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	void shouldDeleteProduct() throws Exception { 
		
		when(adminService.deleteProductById(1L)).thenReturn(CoffeeShopConstant.PRODUCT_DELETED_SUCCESS_MESSAGE);
		
		mockMvc.perform(delete("/admin/delete/{id}",1L))
			      .andExpect(status().isOk())
			      .andDo(print());
	}

}
