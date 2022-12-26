package com.thoughtworks.repospring.controller;

import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductLists() {
        return productService.getProductLists();
    }

    @PostMapping("/increment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/deletion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable UUID id){
        productService.deleteProductById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductById(@RequestBody Product product){
        productService.updateProductById(product);
    }
}
