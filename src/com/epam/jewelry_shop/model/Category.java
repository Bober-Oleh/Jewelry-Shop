package com.epam.jewelry_shop.model;

import java.util.List;

public class Category {
	private int idCategory;
	private String nameCategory;
	private List<Product> productList;

	public Category(int idCategory, String nameCategory) {
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}
}
