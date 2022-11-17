package com.thoughtworks.repospring.Service;

import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.repository.ProductRepository;
import com.thoughtworks.repospring.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    public void setup(){
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldReturnProductList() {
        // given
        Product product = Product.builder().name("cherry")
                .amount("1").weight("3")
                .description("Descriptions for cherry").build();

        //when
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> productLists = productService.getProductLists();

        //then
        Assertions.assertFalse(productLists.isEmpty());
        Assertions.assertEquals(productLists, List.of(product));
        Mockito.verify(productRepository,Mockito.times(1)).findAll();
    }

}
