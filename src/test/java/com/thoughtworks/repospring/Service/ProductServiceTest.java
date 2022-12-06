package com.thoughtworks.repospring.service;

import com.thoughtworks.repospring.common.ProductNotExistException;
import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setup() {
        productService = new ProductService(productRepository);

        UUID id = UUID.randomUUID();
        product = Product.builder()
                .id(id).name("cherry")
                .amount("1").weight("3")
                .description("Descriptions for cherry").build();
    }

    @Test
    void shouldReturnProductList() {
        // given
        when(productRepository.findAll()).thenReturn(List.of(product));

        //when
        List<Product> productLists = productService.getProductLists();

        //then
        Assertions.assertFalse(productLists.isEmpty());
        Assertions.assertEquals(productLists, List.of(product));
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void shouldAddProduct() {
        // given
        when(productRepository.save(product)).thenReturn(product);

        //when
        productService.addProduct(product);
        //then
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldDeleteProductById() {
        // given
        //when
        productService.deleteProductById(product.getId());

        //then
        verify(productRepository,times(1)).deleteById(product.getId());
    }

    @Nested
    class updateProduct {
        @Test
        void shouldUpdateProductById() {
            // given
            UUID id = UUID.randomUUID();

            Product updateProduct = Product.builder()
                    .id(id).name("strawberry")
                    .amount("100").weight("300")
                    .description("Descriptions for strawberry").build();
            when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
            when(productRepository.save(updateProduct)).thenReturn(updateProduct);

            //when
            productService.updateProductById(updateProduct);

            //then
            verify(productRepository, times(1)).save(updateProduct);
            verify(productRepository, times(1)).findById(product.getId());
        }

        @Test
        void shouldThrowExceptionWhenIdNotExist() {
            // given
            when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

            //when
            //then
            Assertions.assertThrows(ProductNotExistException.class, ()-> productService.updateProductById(product));
            verify(productRepository,times(1)).findById(product.getId());
        }
    }

}
