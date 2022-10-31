package com.coffeeshop.advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.coffeeshop.exception.CoffeeShopCustomException;

/**
 * Advice class which convert the exception to customer's required format
 * 
 * @author Ananthan Periyasamy
 */
@RestControllerAdvice
public class ApplicationAdviceController { 

	@ExceptionHandler(CoffeeShopCustomException.class)
	public ResponseEntity<String> coffeeShopCustomException(RuntimeException exception) throws IOException {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception) throws IOException {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
