package com.meg.shoppingcart.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meg.shoppingcart.entity.Customer;
import com.meg.shoppingcart.entity.Product;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


	public Customer getCustomerByName(String name);
	
    public Customer getCustomerByEmailAndName(String email,String name);
    
    @Query("from Customer where email=?1")
	public List<Customer> findByEMAIL(String email);
	
	@Query("from Customer where email=?1 and password=?2")
	public Customer findByUsernamePassword(String username,String password);
}
