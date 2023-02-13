package com.meg.shoppingcart.controller;

import com.meg.shoppingcart.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ViewController {

	private ProductService productService;

	ViewController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("page", "Home");
		model.addAttribute("products", productService.getAllProducts());
		return "index";
	}

	@PostMapping("/productSearch")
	public String getIndex(Model model, @ModelAttribute("productName") String productName) {
		System.out.println("Product Name is  : " + productName);
		model.addAttribute("page", "Home");
		if(productName!=null && productName.length()>0) {
			model.addAttribute("products", productService.getProduct(productName));
		}
		else {
			model.addAttribute("products", productService.getAllProducts());
		}
		return "index";
	}

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("page", "Login");
		return "login";
	}
}
