package com.ultimetric.usecase.model;

public class Product {

	private int id;

	private String productName;

	private double price;

	private String catalogDescription;

	public Product(int id, String productName, double price, String description) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.catalogDescription = description;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCatalogDescription() {
		return catalogDescription;
	}

	public void setCatalogDescription(String catalogDescription) {
		this.catalogDescription = catalogDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalogDescription == null) ? 0 : catalogDescription.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (catalogDescription == null) {
			if (other.catalogDescription != null)
				return false;
		} else if (!catalogDescription.equals(other.catalogDescription))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

}

//CREATE TABLE product (
//		  id INT AUTO_INCREMENT  PRIMARY KEY,
//		  productname VARCHAR(250) NOT NULL,
//		  price INT ,
//		  description VARCHAR(250) DEFAULT NULL
//		);
