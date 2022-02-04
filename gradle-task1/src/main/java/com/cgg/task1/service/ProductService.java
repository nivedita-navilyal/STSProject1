package com.cgg.task1.service;

import java.util.List;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.exception.ServiceException;

public interface ProductService {
	
	public ProductDto saveProduct(ProductDto productDto) throws ServiceException;
	public List<ProductDto> findAllProducts() throws ServiceException;
	public ProductDto getProductById(int id) throws ServiceException;
	public String deleteProductById(int id) throws ServiceException;

	public String healthCheck(int id);

	
}
