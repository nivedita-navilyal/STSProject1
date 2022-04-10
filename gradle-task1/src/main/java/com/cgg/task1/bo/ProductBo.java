package com.cgg.task1.bo;

import java.util.List;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.exception.ServiceException;

public interface ProductBo {
	public ProductDto saveProduct(ProductDto productDto) throws ServiceException;
	public List<ProductDto> findAllProducts() throws ServiceException;
	public ProductDto getProductById(int id) throws ServiceException;
	public ProductDto deleteProductById(int id) throws ServiceException;

	public List<ProductDto> healthCheck();

}
