package com.meg.shoppingcart.controller;

import com.meg.shoppingcart.service.CartService;
import com.meg.shoppingcart.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CartController {

	private CartService cartService;
	private ProductService productService;

	CartController(final CartService cartService, final  ProductService productService) {
		this.cartService = cartService;
		this.productService = productService;
	}

	@GetMapping(value = "/cart")
	public String getCart(Model model) {
		model.addAttribute("page", "Cart");
		model.addAttribute("items", cartService.getItems());
		model.addAttribute("total", cartService.getTotal());
		return "cart";
	}

	@PostMapping(value = "/cart")
	public String addToCart(Model model, @ModelAttribute("productId") Integer productId) {
		cartService.addItem(productService.getProduct(productId));
		model.addAttribute("mode", "add");
		return getCart(model);
	}
}
