package com.cgg.task1.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.eo.ProductEo;
import com.cgg.task1.exception.ServiceException;

@Component
public class ProductBoImpl implements ProductBo{

	@Autowired 
	private ProductEo productEo;
	
	Logger logger=LoggerFactory.getLogger(ProductBo.class);
	
	@Override
	public ProductDto saveProduct(ProductDto productDto) throws ServiceException {
		logger.info("Product details saved in BO layer");
		return productEo.saveProduct(productDto);
	}

	@Override
	public List<ProductDto> findAllProducts() throws ServiceException {
		logger.info("Product details fetched in BO layer");
		return productEo.findAllProducts();
	}

	@Override
	public ProductDto getProductById(int id) throws ServiceException {
		logger.info("fetched details of given product Id  in BO layer");
		return productEo.getProductById(id);
	}

	@Override
	public ProductDto deleteProductById(int id) throws ServiceException {
		logger.info("Product details deleted of given Id  in BO layer");
		return productEo.deleteProductById(id);
		
	}

	@Override
	public List<ProductDto> healthCheck() {
		logger.info("HealthCheck in BO layer");
		return productEo.healthCheck();
	}

}
