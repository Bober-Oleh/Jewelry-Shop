package com.epam.jewelry_shop.dao;

import java.util.List;
import com.epam.jewelry_shop.model.Category;

public interface CategoryDAO {
	List<Category> findAllCategory();
}
