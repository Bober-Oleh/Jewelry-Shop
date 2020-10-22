package com.epam.jewelry_shop.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.epam.jewelry_shop.model.Category;

public class CategoryResultSetExtracter {

	public Category createCategoryFromResultSet(ResultSet rs) throws SQLException {

		int idCategory = rs.getInt("idCategory");
		String nameCategory = rs.getString("nameCategory");
		Category category = new Category(idCategory, nameCategory);

		return category;
	}
}
