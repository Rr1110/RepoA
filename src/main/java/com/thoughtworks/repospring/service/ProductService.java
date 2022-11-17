package com.thoughtworks.repospring.service;

import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductLists() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public void addProduct(Product product) {
        Product newProduct = Product.builder()
                .name(product.getName())
                .amount(product.getAmount())
                .description(product.getDescription())
                .weight(product.getWeight()).build();
        productRepository.save(newProduct);
    }


    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }
}
