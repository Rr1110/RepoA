package com.thoughtworks.repospring.Service;

import com.thoughtworks.repospring.common.ProductListException;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    public void setup() {
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldReturnProductList() {
        // given
        Product product = Product.builder().name("cherry")
                .amount("1").weight("3")
                .description("Descriptions for cherry").build();
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
        Product product = Product.builder().name("cherry")
                .amount("1").weight("3")
                .description("Descriptions for cherry").build();
        when(productRepository.save(product)).thenReturn(product);

        //when
        productService.addProduct(product);
        //then
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldDeleteProductById() {
        // given
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .id(id).name("cherry")
                .amount("1").weight("3")
                .description("Descriptions for cherry").build();

        //when
        productService.deleteProductById(product.getId());

        //then
        verify(productRepository,times(1)).deleteById(product.getId());
    }

    @Test
    void shouldUpdateProductById(){
        // given
        UUID id = UUID.randomUUID();
        Product oldProduct = Product.builder()
                .id(id).name("cherry")
                .amount("1").weight("3")
                .description("Descriptions for cherry").build();

        Product updateProduct = Product.builder()
                .id(id).name("strawberry")
                .amount("100").weight("300")
                .description("Descriptions for strawberry").build();
        when(productRepository.findById(oldProduct.getId())).thenReturn(Optional.of(oldProduct));
        when(productRepository.save(updateProduct)).thenReturn(updateProduct);

        //when
        productService.updateProductById(updateProduct);

        //then
        verify(productRepository,times(1)).save(updateProduct);
        verify(productRepository,times(1)).findById(oldProduct.getId());
    }

}
