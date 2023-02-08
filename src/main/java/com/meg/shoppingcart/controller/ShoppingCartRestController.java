package com.meg.shoppingcart.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meg.shoppingcart.dto.OrderDTO;
import com.meg.shoppingcart.dto.ResponseOrderDTO;
import com.meg.shoppingcart.entity.Customer;
import com.meg.shoppingcart.entity.Order;
import com.meg.shoppingcart.entity.Product;
import com.meg.shoppingcart.service.CustomerService;
import com.meg.shoppingcart.service.OrderService;
import com.meg.shoppingcart.service.ProductService;
import com.meg.shoppingcart.util.DateUtil;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {

    private OrderService orderService;
    private ProductService productService;
    private CustomerService customerService;

    public ShoppingCartRestController(OrderService orderService, ProductService productService, CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    private Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }
    
    @GetMapping(value = "/getProduct/{productName}")
    public ResponseEntity<Product> getOrderDetails(@PathVariable String productName) {

        Product product = productService.getProduct(productName);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems());

        Customer customer = new Customer(orderDTO.getCustomerName(), orderDTO.getCustomerEmail());
        Integer customerIdFromDb = customerService.isCustomerPresent(customer);
        if (customerIdFromDb != null) {
            customer.setId(customerIdFromDb);
            logger.info("Customer already present in db with id : " + customerIdFromDb);
        }else{
            customer = customerService.saveCustomer(customer);
            logger.info("Customer saved.. with id : " + customer.getId());
        }
        Order order = new Order(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
        order = orderService.saveOrder(order);
        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        logger.info("test push..");

        return ResponseEntity.ok(responseOrderDTO);
    }

    /**
     * Customer API code for fetching customers, Registering Customers
     */
    
    @GetMapping(value = "/getCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customerList = customerService.getAllCustomers();

        return ResponseEntity.ok(customerList);
    }

    @GetMapping(value = "/getCustomer/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {

    	Customer customer = customerService.getCustomerByName(name);
        return ResponseEntity.ok(customer);
    }
    
    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
    	customerService.saveCustomer(customer);
    	return ResponseEntity.ok(customer);
    }

}