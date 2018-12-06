package com.example.demo.service;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    ProductsRepository productRepo;

    public Product getProduct(Long id){
        return productRepo
                .findById(id)
                .orElse(null);
    }

    public List<Product> getAllProducts(){
        List<Product> products =  StreamSupport.stream(productRepo.findAll().spliterator(),false).collect(Collectors.toList());
        return products;
    }

    public Product insertProduct(Product product){
        return productRepo.save(product);
    }

    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }

    public Product updateProduct(Long id,Product product) throws CustomException {
        Product productUpdated = productRepo.findById(id).orElseThrow(() -> new CustomException("Product not available to update!!!"));
        productUpdated.setName(product.getName());
        productUpdated.setPrice(product.getPrice());
        return productRepo.save(productUpdated);
    }


}
