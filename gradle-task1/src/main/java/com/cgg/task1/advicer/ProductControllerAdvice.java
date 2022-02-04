package com.cgg.task1.advicer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cgg.task1.exception.NoRecordFoundException;
import com.cgg.task1.exception.ProductNotFoundException;
import com.cgg.task1.exception.ServiceException;

@ControllerAdvice
public class ProductControllerAdvice {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProuctNotFoundException(ProductNotFoundException productNotFoundException){
		return new ResponseEntity<String>("Product Not Found with given Id",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<String> handleNoRecordFoundException(NoRecordFoundException nRecordFoundException){
		return new ResponseEntity<String>("No Records Found",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<String> handleServiceException(ServiceException serviceException){
		return new ResponseEntity<String>("No Records Found",HttpStatus.BAD_REQUEST);
	}

}
