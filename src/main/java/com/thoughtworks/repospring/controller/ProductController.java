package com.thoughtworks.repospring.controller;

import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.modal.UpdateProductRequest;
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
    public void deleteProductById(@PathVariable UUID id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductById(@PathVariable UUID id, @RequestBody UpdateProductRequest updateProductRequest) {
        productService.updateProductById(id, updateProductRequest);
    }

    @GetMapping("list/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductByName(@PathVariable String name, @RequestParam(required = false) String weight) {
        return productService.getProductByName(name, weight);
    }
}
