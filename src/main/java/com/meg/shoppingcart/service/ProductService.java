package com.meg.shoppingcart.service;

import org.springframework.stereotype.Service;

import com.meg.shoppingcart.entity.Product;
import com.meg.shoppingcart.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public  Product getProduct(int id) {
        return productRepository.findById(id).get();
    }

    public Product getProduct(String name) {
    	return this.productRepository.getProductByName(name);
    }
}
