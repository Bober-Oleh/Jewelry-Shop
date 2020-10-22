package com.epam.jewelry_shop.model;

import java.util.List;

public class Producer {
	private int idProducer;
	private String nameProducer;
	private List<Product> productList;

	public Producer(int idProducer, String nameProducer) {
		this.idProducer = idProducer;
		this.nameProducer = nameProducer;
	}

	public int getIdProducer() {
		return idProducer;
	}

	public String getNameProducer() {
		return nameProducer;
	}
	
}
