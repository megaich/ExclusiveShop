package com.meg.shoppingcart.controller;

import com.meg.shoppingcart.service.CartService;
import com.meg.shoppingcart.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CheckoutController {

	private CartService cartService;
	private ProductService productService;

	CheckoutController(final CartService cartService, final  ProductService productService) {
		this.cartService = cartService;
		this.productService = productService;
	}

	@GetMapping("/checkout")
	public String getCheckout(Model model) {
		model.addAttribute("page", "Checkout");
		model.addAttribute("items", cartService.getItems());
		model.addAttribute("total", cartService.getTotal());
		return "checkout";
	}
}
