package com.meg.shoppingcart.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.meg.shoppingcart.entity.Product;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartService {
	private Map<Product, Integer> items = new HashMap<>();

	public Map<Product, Integer> getItems() {
		return Collections.unmodifiableMap(items);
	}

	public void addItem(Product product) {
		if (items.containsKey(product)) {
			items.replace(product, items.get(product) + 1);
		} else {
			items.put(product, 1);
		}
	}

	public void removeItem(Product product) {
		if (items.containsKey(product)) {
			if (items.get(product) > 1)
				items.replace(product, items.get(product) - 1);
			else if (items.get(product) == 1) {
				items.remove(product);
			}
		}
	}

	public BigDecimal getTotal() {
		return items.entrySet().stream()
				.map(entry -> BigDecimal.valueOf(entry.getKey().getPrice() * entry.getValue()))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
}
