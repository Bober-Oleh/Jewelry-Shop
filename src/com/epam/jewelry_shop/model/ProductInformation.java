package com.epam.jewelry_shop.model;

public class ProductInformation {
	private int idProduct;
	private String nameProduct;
	private int numberOfProducts;
	private double price;

	public ProductInformation() {
	}

	public ProductInformation(int idProduct, String nameProduct, int numberOfProducts, double price) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.numberOfProducts = numberOfProducts;
		this.price = price;

	}

	public ProductInformation(int idProduct, double price, int numberOfProducts) {

		super();
		this.idProduct = idProduct;
		this.numberOfProducts = numberOfProducts;
		this.price = price;

	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductInformation [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", numberOfProducts="
				+ numberOfProducts + ", price=" + price + "]";
	}
}
