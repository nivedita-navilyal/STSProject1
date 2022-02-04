package com.cgg.task1.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgg.task1.controller.ProductController;
import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.entities.Product;
import com.cgg.task1.exception.NoRecordFoundException;
import com.cgg.task1.exception.ProductNotFoundException;
import com.cgg.task1.exception.ServiceException;
import com.cgg.task1.mapper.ProductMapper;
import com.cgg.task1.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired 
	ProductMapper productMapper;
	
	Logger logger=LoggerFactory.getLogger(ProductController.class);

	
	@Override
	public ProductDto saveProduct(ProductDto productDto) throws ServiceException {
		try {
			Product product = productRepository.save(productMapper.dtoToModel(productDto));
			return productMapper.modelToDto(product);
		}catch(Exception e) {
			logger.error("Could not add product");
			throw new ServiceException("Could not add product"+e);	
		}
	}

	@Override
	public List<ProductDto> findAllProducts() throws ServiceException {
		List<Product> productVo=productRepository.findAll();
		try {
			if(!productVo.isEmpty()) {
				return productMapper.modelsToDtos(productVo);
			}
			else throw new NoRecordFoundException("Product List is empty");
		}
		catch(NoRecordFoundException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	
	}

	@Override
	public ProductDto getProductById(int id) throws ServiceException {
		try {
			Product productId =productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("product doesnot exists"));
			return productMapper.modelToDto(productId);
		}catch(ProductNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public String deleteProductById(int id) throws ServiceException {
		try {
			if(productRepository.existsById(id)) {
				productRepository.deleteById(id);
			}
			else throw new ProductNotFoundException("Id doesnot Exists!!!!");
		}
		catch(ProductNotFoundException e) {
				throw new ServiceException(e.getMessage());
			}
		return "Product Deleted Successfully";
	}

	@Override
	public String healthCheck(int id) {
		Optional<Product> pro= productRepository.findById(id);
		
		String check="";
		if(pro.isPresent()) {
			pro.get();
			check= "Health Checked Successfully";
		}
		else {
			check = "Health Failed";
		}
		return check;	
		
	}
	
	
}

	
	

//	@Override
//	public Product saveProduct(Product product) {
//		return productRepository.save(product);
//	}
//
//	@Override
//	public List<Product> findAllProducts() {
//		return productRepository.findAll();
//	}
//
//	@Override
//	public String getProductById(int id) throws ProductNotFoundException {
//		Optional<Product> pr = productRepository.findById(id);
//		String ans="";
//		if(pr.isPresent()) {
//			pr.get();
//			ans= "Database Connection Succeed. Product Data fetched";
//		}
//		else {
//			ans="Product not found with given id. Connection Refused";
//		}
//		return ans;
//	}
//
//	@Override
//	public Product getProductWithId(int id) throws ProductNotFoundException {
//		Optional<Product> pr = productRepository.findById(id); 
//		Product product = null;
//		if(pr.isPresent()) {
//			product = pr.get();
//			return product;
//		}
//		else {
//			throw new ProductNotFoundException("Product not found with given Id");
//		}
//	}
	




