package com.epam.jewelry_shop.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import com.epam.jewelry_shop.model.Product;

public class BasketService {
	private Map<Product, Integer> basketMap;

	public BasketService() {
		this.basketMap = new LinkedHashMap<>();
	}

	public Set<Product> getProductList() {
		return basketMap.keySet();
	}

	public void cleanTarget() {
		this.basketMap = new LinkedHashMap<>();
	}

	public Map<Product, Integer> getBasketMap() {
		return basketMap;
	}

	public void addProduct(Product product) {
		if (basketMap.containsKey(product)) {
			int oldValue = basketMap.get(product);
			basketMap.put(product, oldValue + 1);
		} else {
			basketMap.put(product, 1);
		}
	}

	public boolean deleteProduct(int id) {

		for (Product p : basketMap.keySet()) {
			if (p.getIdProduct() == id) {
				basketMap.remove(p);
				return true;
			}
		}
		return false;
	}

	public int getNumberProducts() {
		return basketMap.size();
	}

	public double getTotalPrice() {
		if (getNumberProducts() == 0) {
			return 0;
		}

		return basketMap.entrySet().stream().map(s -> (double) s.getKey().getPrice() * (double) s.getValue())
				.reduce((x, y) -> x + y).get();
	}

	public boolean setNumberProduct(int id, int number) {
		for (Product p : basketMap.keySet()) {
			if (p.getIdProduct() == id) {
				basketMap.put(p, number);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Basket{" + "basketMap=" + basketMap + '}';
	}
}