package com.thoughtworks.repospring.service;

import com.thoughtworks.repospring.common.ProductNotExistException;
import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.modal.UpdateProductRequest;
import com.thoughtworks.repospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public void updateProductById(UUID id, UpdateProductRequest updateProductRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product updateProduct = optionalProduct.get();
            updateProduct.setAmount(updateProductRequest.getAmount());
            updateProduct.setWeight(updateProductRequest.getWeight());
            updateProduct.setDescription(updateProductRequest.getDescription());
            productRepository.save(updateProduct);
        }else{
            throw new ProductNotExistException("Product not found");
        }
    }

    public List<Product> getProductByName(String name, String weight) {
        List<Product> findProductByName = productRepository.findProductByName(name);
        if (weight == null) {
            return findProductByName;
        }else{
            return findProductByName.stream()
                    .filter(product -> product.getWeight().equals(weight))
                    .collect(Collectors.toList());
        }
    }
}
