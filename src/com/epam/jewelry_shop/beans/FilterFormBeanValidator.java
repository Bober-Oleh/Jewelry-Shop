package com.epam.jewelry_shop.beans;

import javax.servlet.http.HttpServletRequest;

public class FilterFormBeanValidator {
	public FilterFormBean filterFormBeanValidate(HttpServletRequest request) {
		FilterFormBean filterBean = new FilterFormBean();
		String categoryId = request.getParameter("category");
		String producerId = request.getParameter("producer");
		String name = request.getParameter("name");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		if (isNumeric(categoryId)) {
			filterBean.setCategoryId(Integer.parseInt(categoryId));
		}
		if (isNumeric(producerId)) {
			filterBean.setProducerId(Integer.parseInt(producerId));
		}

		if (name.length() < 100) {
			filterBean.setName(name);
		}
		if (isNumeric(minPrice)) {
			filterBean.setMinPrice(Double.parseDouble(minPrice));
		}
		if (isNumeric(minPrice)) {
			filterBean.setMaxPrice(Double.parseDouble(maxPrice));
		}

		return filterBean;

	}

	public static boolean isNumeric(String str) {
		if (str != null) {
			return str.matches("-?\\d+(\\.\\d+)?");
		}

		return false;
	}
}