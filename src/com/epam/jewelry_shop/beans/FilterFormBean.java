package com.epam.jewelry_shop.beans;

import java.io.Serializable;

public class FilterFormBean implements Serializable {
	private int categoryId;
	private int producerId;
	private double minPrice;
	private double maxPrice;
	private String name;

	public FilterFormBean() {
	}

	public FilterFormBean(int categoryId, int producerId, double minPrice, double maxPrice, String name) {
		super();
		this.categoryId = categoryId;
		this.producerId = producerId;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProducerId() {
		return producerId;
	}

	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
