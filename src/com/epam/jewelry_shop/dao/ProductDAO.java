package com.epam.jewelry_shop.dao;

import com.epam.jewelry_shop.beans.FilterFormBean;
import com.epam.jewelry_shop.repository.ProductLayer;

public interface ProductDAO extends ProductLayer {
	String createFiltersForQuery(FilterFormBean filterBean);

	String createSortForQuery(String fieldSort, int typeSort);

}