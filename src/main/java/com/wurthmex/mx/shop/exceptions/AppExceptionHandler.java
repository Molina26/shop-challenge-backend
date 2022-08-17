package com.wurthmex.mx.shop.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.wurthmex.mx.shop.payload.error.response.ValidationErrors;

@ControllerAdvice
public class AppExceptionHandler {
  

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleValidationErrorException(MethodArgumentNotValidException ex, WebRequest webRequest) {
		Map<String, String> errors = new HashMap<>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errors.put(fieldName, errorMessage);
		}
		
		ValidationErrors validationErrors = new ValidationErrors(errors, new Date());
		
		ResponseEntity<Object> response = new ResponseEntity<>(validationErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
		return response;
	}

	@ExceptionHandler({AppCustomException.class})
	public ResponseEntity<Object> capturarErrorPersonal(AppCustomException ex) {
		Map<String, String> errors = new HashMap<>();

		errors.put(ex.getField(), ex.getMessage());

		ValidationErrors validationErrors = new ValidationErrors(errors, new Date());
		
		ResponseEntity<Object> response = new ResponseEntity<>(validationErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
		return response;
	}
}
