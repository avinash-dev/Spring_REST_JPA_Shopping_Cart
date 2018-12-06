package com.example.demo.controller;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/{id}")
    public Product getProducts(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping(path="/")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping(path="/")
    public Product saveProduct(@RequestBody Product product){
        return productService.insertProduct(product);
    }

    @PutMapping(path="/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product) throws CustomException {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping(path="/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
