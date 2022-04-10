package com.cgg.task1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cgg.task1.controller.ProductController;
import com.cgg.task1.service.ProductService;
import com.cgg.task1.vo.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class GradleTask1ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

//	@BeforeAll
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
//	}


	// =========================================== Get All Products ==========================================

	@Test
	public void findAllProducts() throws Exception {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product(1, "redmi", "8gb", 9000, 3, "my company");
		Product product2 = new Product(2, "cool", "4gb", 8000, 7, "my company");
		Product product3 = new Product(3, "jio", "6gb", 5000, 3, "my company");
		Product product4 = new Product(4, "samsung", "8gb", 12000, 4, "my company");
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);

		Mockito.when(productService.findAllProducts()).thenReturn(products);
		mockMvc.perform(get("/product/api/getAllproducts")).andExpect(status().isOk()).andReturn();
		assertEquals(products.size(), 4);
		
//		List result = productService.findAllProducts();
//		Mockito.verify(productService).findAllProducts();

	}

	// =========================================== Get Product By ID  ==========================================

	@Test
	public void getProductById() throws Exception {
		Product product = new Product(1, "redmi","8gb", 9000, 3, "my company");
		Mockito.when(productService.getProductById(product.getId())).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/product/api/getproducts/1"))
				.andExpect(jsonPath("name", is("redmi")));

	}

	// =========================================== Create New Product ==========================================

	@Test
	public void saveProduct() throws Exception {
		Product product = new Product(1, "redmi", "8gb", 9000, 3, "my company");

		String jsonRequest = objectMapper.writeValueAsString(product);
		Mockito.when(productService.saveProduct(product)).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders
				.post("/product/api/addproduct")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	// =========================================== Delete Product ==========================================

	@Test
	public void deleteProductById() throws Exception {
		Product product1 = new Product(1, "samsung", "8gb", 8000, 4, "my company");
		Mockito.when(productService.getProductById(product1.getId())).thenReturn(product1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/product/api/deleteProductById/{id}", product1.getId()))
				.andExpect(status().isOk());
	//	verify(productService, times(1)).getProductById(productDto1.getId());
		verify(productService, times(1)).deleteProductById(product1.getId());
		

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
