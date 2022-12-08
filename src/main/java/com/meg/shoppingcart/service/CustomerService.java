package com.meg.shoppingcart.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.meg.shoppingcart.entity.Customer;
import com.meg.shoppingcart.entity.Product;
import com.meg.shoppingcart.repository.CustomerRepository;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Integer isCustomerPresent(Customer customer){
        Customer customer1 = customerRepository.getCustomerByEmailAndName(customer.getEmail(),customer.getName());
        return customer1!=null ? customer1.getId(): null ;
    }
    
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }
    
    public Customer getCustomer(int id) {
    	return this.customerRepository.getById(id);
    }
    
    public Customer getCustomerByName(String name) {
    	return this.customerRepository.getCustomerByName(name);
    }
    
    public Customer getCustomerByEmailAndName(String email,String name) {
    	return this.customerRepository.getCustomerByEmailAndName(email, name);
    }
    
    public List<Customer> getCustomerByEmail(String email) {
    	return this.customerRepository.findByEMAIL(email);
    } 
    
    public Customer findByUsernamePassword(String username,String password) {
    	return this.customerRepository.findByUsernamePassword(username, password);
    }
}
