package com.thoughtworks.repospring.controller;

import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Product> productJacksonTester;

    private Product product;

    @BeforeEach
    public void beforeEach() {
        product = Product.builder().name("product Name")
                .weight("10").amount("2").description("des").build();
    }

    @Test
    void shouldReturnProductList() throws Exception {

        mockMvc.perform(get("/products/lists"))
                //then
                .andExpect(status().isOk());

    }

    @Test
    void shouldAddProductSuccess() throws Exception {

        mockMvc.perform(post("/products/increment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJacksonTester.write(product).getJson()))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldDeleteProductSuccessById() throws Exception {
        UUID id = UUID.fromString("93ade9ce-3f92-4608-8e5e-0fad25f92e9e");

        mockMvc.perform(delete("/products/deletion/{id}", id))
                .andExpect(status().isOk());
    }


    @Test
    void shouldUpdateProductSuccess() throws Exception {

        UUID id = UUID.fromString("a6ee8e9a-1e30-44ec-aed7-bdf687b23df2");
        Product newProduct = Product.builder().id(id).name("new product Name")
                .weight("10").amount("2").description("description").build();

        mockMvc.perform(put("/products/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJacksonTester.write(newProduct).getJson()))

                .andExpect(status().isOk());
    }

}
