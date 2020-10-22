package com.epam.jewelry_shop.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.epam.jewelry_shop.model.Product;

public class ProductResultSetExtracter {
	public Product createProductFromResultSet(ResultSet rs) throws SQLException {

		int idProduct = rs.getInt("idProduct");
		double price = rs.getDouble("price");
		String name = rs.getString("name");
		String image = rs.getString("image");
		
		Product product = new Product(idProduct, price, name, image);
		return product;
	}

}
