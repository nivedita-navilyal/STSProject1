package com.cgg.task1.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.entities.Product;
import com.cgg.task1.exception.ProductNotFoundException;
import com.cgg.task1.exception.ServiceException;
import com.cgg.task1.mapper.ProductMapper;
import com.cgg.task1.service.ProductService;

@RestController
@RequestMapping("/product/api")
public class ProductController {
	
	@Autowired 
	private ProductService productService;

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome nivedita";
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productdto)throws ServiceException {
		logger.info("Product Added Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productdto));

	}

	@GetMapping("/getAllproducts")
	public ResponseEntity<List<ProductDto>> findAll()throws ServiceException  {
		
		logger.info("Products fetched Successfully");
		List<ProductDto> productList = productService.findAllProducts();
		if(productList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		return ResponseEntity.ok(productList);

	}

	@GetMapping("/getproducts/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable int id) throws ServiceException  {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
	}
	
	@DeleteMapping("/deleteProductById/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id) throws ServiceException{
		logger.info("Inside deleteProduct method");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.deleteProductById(id));
	}
	
	

	@GetMapping("/healthcheck/{id}")
	public ResponseEntity<String> gethealthCheck(@PathVariable("id") Integer id) throws ProductNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(productService.healthCheck(id));
	}

}
