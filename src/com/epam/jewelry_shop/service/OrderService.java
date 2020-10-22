package com.epam.jewelry_shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.epam.jewelry_shop.dao.OrderDAOImpl;
import com.epam.jewelry_shop.model.Order;
import com.epam.jewelry_shop.model.Product;
import com.epam.jewelry_shop.model.ProductInformation;
import com.epam.jewelry_shop.model.User;

public class OrderService {
	public Order getOrderFromBasket(Map<Product, Integer> target, User user) {
		List<ProductInformation> productList = new ArrayList();
		for (Product p : target.keySet()) {
			productList.add(new ProductInformation(p.getIdProduct(), p.getName(), target.get(p), p.getPrice()));
		}

		Order order = new Order(new Date().toString(), user, productList);

		return order;
	}

	public void saveOrder(Order order) {
		new OrderDAOImpl().addOrder(order);
	}
}
