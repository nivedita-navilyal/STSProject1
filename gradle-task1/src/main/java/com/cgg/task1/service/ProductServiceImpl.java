package com.cgg.task1.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgg.task1.bo.ProductBo;
import com.cgg.task1.controller.ProductController;
import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.exception.NoRecordFoundException;
import com.cgg.task1.exception.ProductNotFoundException;
import com.cgg.task1.exception.ServiceException;
import com.cgg.task1.mapper.ProductMapper;
import com.cgg.task1.repository.ProductRepository;
import com.cgg.task1.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductBo productBo;

	@Autowired
	ProductMapper productMapper;

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Override
	public Product saveProduct(Product product) throws ServiceException {
		try {
			ProductDto productDto = productBo.saveProduct(productMapper.modelToDto(product));
			return productMapper.dtoToModel(productDto);
		} catch (Exception e) {
			logger.error("Could not add product");
			throw new ServiceException("Could not add product" + e);
		}
	}

	@Override
	public List<Product> findAllProducts() throws ServiceException {
		List<ProductDto> productDto = productBo.findAllProducts();
		try {
			if (!productDto.isEmpty()) {
				return productMapper.dtosToModels(productDto);

			} else
				throw new NoRecordFoundException("Product List is empty");
		} catch (NoRecordFoundException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public Product getProductById(int id) throws ServiceException {
		logger.info("Fetched details of given product id in service layer");
		return productMapper.dtoToModel(productBo.getProductById(id));
//		try {
//			ProductDto productId = productBo.getProductById(id).orElseThrow(()->new ProductNotFoundException("product doesnot exists"));
//			return productMapper.dtoToModel(productId);
//			// return productMapper.dtoToModel(productBo.getProductById(id));
//		} 
//		catch (ProductNotFoundException e) {
//			throw new ServiceException(e.getMessage());
//		}

	}

	@Override
	public String deleteProductById(int id) throws ServiceException {
		try {
			if (productRepository.existsById(id)) {
				productRepository.deleteById(id);
			} else
				throw new ProductNotFoundException("Id doesnot Exists!!!!");
		} catch (ProductNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
		return "Product Deleted Successfully";
	}

	@Override
	public String healthCheck(int id) {

		Optional<ProductDto> pro = productRepository.findById(id);

		if (pro.isPresent()) {
			pro.get();
			return "Health Checked Successfully";
		} else {
			return "Health Failed";
		}

	}

}
