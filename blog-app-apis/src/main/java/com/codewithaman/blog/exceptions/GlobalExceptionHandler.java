package com.codewithaman.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithaman.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNotFoundException(ResourceNotFoundException ex) {
		String massage = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(massage, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException( MethodArgumentNotValidException ex){
		
		Map<String,String> resp= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			resp.put(fieldName, defaultMessage);
			
		});
		
		
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> apiException(ApiException ex) {
		String massage = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(massage, true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);

	}
}
