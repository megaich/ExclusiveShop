package com.meg.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meg.shoppingcart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	public Product getProductByName(String name);
	
}


