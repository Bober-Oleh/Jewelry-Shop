package com.epam.jewelry_shop.repository;

import java.util.List;
import com.epam.jewelry_shop.model.Product;

public interface ProductLayer {
	List<Product> findAllProductsWithFilters(String query);

	Product getProductById(int idProduct);
}
