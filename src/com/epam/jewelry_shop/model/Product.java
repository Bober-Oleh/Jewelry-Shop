package com.epam.jewelry_shop.model;

public class Product {
	private int idProduct;
	private double price;
	private String name;
	private Category category;
	private Producer producer;
	private String image;

	public Product() {
	}

	public Product(int idProduct, double price, String name, String image) {
		super();
		this.idProduct = idProduct;
		this.price = price;
		this.name = name;
		this.image = image;
	}


	public Product(int idProduct, double price, String name, Category category, Producer producer, String image) {
		this.idProduct = idProduct;
		this.price = price;
		this.name = name;
		this.category = category;
		this.producer = producer;
		this.image = image;
	}

	public Product(int idProduct, double price, String name) {
		this.idProduct = idProduct;
		this.price = price;
		this.name = name;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public String getImage() {
		return image;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + this.idProduct;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Product other = (Product) obj;
		if (this.idProduct != other.idProduct) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Product{" + "idProduct=" + idProduct + ", price=" + price + ", name=" + name + '}';
	}

}