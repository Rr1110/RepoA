package com.thoughtworks.repospring.service;

import com.thoughtworks.repospring.common.ProductListException;
import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductLists() {
        List<Product> productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            return productList;
        }
        throw new ProductListException("Product list is null");
    }

    public void addProduct(Product product) {
            Product newProduct = Product.builder()
                    .name(product.getName())
                    .amount(product.getAmount())
                    .description(product.getDescription())
                    .weight(product.getWeight()).build();
            productRepository.save(newProduct);
    }

}
