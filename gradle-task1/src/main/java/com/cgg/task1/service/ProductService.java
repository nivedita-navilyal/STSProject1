package com.cgg.task1.service;

import java.util.List;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.exception.ServiceException;
import com.cgg.task1.vo.Product;

public interface ProductService {
	
	public Product saveProduct(Product product) throws ServiceException;
	public List<Product> findAllProducts() throws ServiceException;
	public Product getProductById(int id) throws ServiceException;
	public String deleteProductById(int id) throws ServiceException;

//	public List<Product healthCheck();
	public String healthCheck(int id);

	
}
