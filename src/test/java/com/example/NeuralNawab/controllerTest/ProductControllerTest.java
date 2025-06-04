package com.example.NeuralNawab.controllerTest;

import com.example.NeuralNawab.controller.ProductController;
import com.example.NeuralNawab.model.Product;
import com.example.NeuralNawab.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product testProduct;
    private List<Product> testProducts;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setProdId(1);
        testProduct.setProdName("Test Product");
        testProduct.setPrice(99);

        Product product2 = new Product();
        product2.setProdId(2);
        product2.setProdName("Another Product");
        product2.setPrice(49);

        testProducts = Arrays.asList(testProduct, product2);
    }

    @Test
    void getProducts_ShouldReturnAllProducts() throws Exception {
        when(productService.getProducts()).thenReturn(testProducts);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].prodId").value(1))
                .andExpect(jsonPath("$[0].prodName").value("Test Product"))
                .andExpect(jsonPath("$[0].price").value(99))
                .andExpect(jsonPath("$[1].prodId").value(2));

        verify(productService, times(1)).getProducts();
    }

}
