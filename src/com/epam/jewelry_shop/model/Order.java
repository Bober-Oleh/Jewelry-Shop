package com.epam.jewelry_shop.model;

import java.util.List;

public class Order {

	private int idOrder;
	private String orderStatus;
	private String detailedOrderStatus;
	private String date;
	private User user;
	private List<ProductInformation> listProduct;

	public Order() {
	}

	public Order(String date, User user, List<ProductInformation> listProduct) {
		this.date = date;
		this.user = user;
		this.listProduct = listProduct;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDetailedOrderStatus() {
		return detailedOrderStatus;
	}

	public void setDetailedOrderStatus(String detailedOrderStatus) {
		this.detailedOrderStatus = detailedOrderStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ProductInformation> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductInformation> listProduct) {
		this.listProduct = listProduct;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", orderStatus=" + orderStatus + ", detailedOrderStatus="
				+ detailedOrderStatus + ", date=" + date + "]";
	}
}
