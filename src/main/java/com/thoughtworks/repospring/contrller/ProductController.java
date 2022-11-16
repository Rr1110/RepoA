package com.thoughtworks.repospring.contrller;

import com.thoughtworks.repospring.modal.Product;
import com.thoughtworks.repospring.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductLists(){
        return productService.getProductLists();
    }
}
