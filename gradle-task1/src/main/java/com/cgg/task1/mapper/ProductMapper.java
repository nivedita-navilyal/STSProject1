package com.cgg.task1.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.cgg.task1.dto.ProductDto;
import com.cgg.task1.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	//toEmployeeDto
	ProductDto modelToDto(Product product);

	List<ProductDto> modelsToDtos(List<Product> product);

	//fromEmployeeDto
	Product dtoToModel(ProductDto productDto);
	
	List<Product> dtosToModels(List<ProductDto> productDto);
	

}
