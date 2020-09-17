package com.ultimetric.usecase.model;

public class PlaceOrderDetails {

	private Customer customer;
	private Product product;;
	private String placeOrderStatus;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPlaceOrderStatus() {
		return placeOrderStatus;
	}

	public void setPlaceOrderStatus(String placeOrderStatus) {
		this.placeOrderStatus = placeOrderStatus;
	}

}
