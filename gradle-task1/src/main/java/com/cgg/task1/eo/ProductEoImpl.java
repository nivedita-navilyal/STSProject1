package com.cgg.task1.eo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.exception.ServiceException;
import com.cgg.task1.repository.ProductRepository;

@Component
public class ProductEoImpl implements ProductEo{

	@Autowired
	private ProductRepository productRepository;
	
	Logger logger= LoggerFactory.getLogger(ProductEo.class);
	
	@Override
	public ProductDto saveProduct(ProductDto productDto) throws ServiceException {
		logger.info("Product details saved in EO layer");
		return productRepository.save(productDto);
	}

	@Override
	public List<ProductDto> findAllProducts() throws ServiceException {
		logger.info("Product details fetched in EO layer");
		return productRepository.findAll();
	}

	@Override
	public ProductDto getProductById(int id) throws ServiceException {
		logger.info("fetched details of given product Id  in EO layer");
		return productRepository.getById(id);
	}

	@Override
	public ProductDto deleteProductById(int id) throws ServiceException {
		logger.info("Product details deleted of given Id  in EO layer");
	//	return productRepository.deleteById(id);
		return null;
		
	}

	@Override
	public List<ProductDto> healthCheck() {
		try {
			logger.info("HealthCheck Successfull");
			return productRepository.findAll();
		}catch(Exception e) {
			logger.info("Health Check Failed");
			return null;
		}
		
	}


	

}
