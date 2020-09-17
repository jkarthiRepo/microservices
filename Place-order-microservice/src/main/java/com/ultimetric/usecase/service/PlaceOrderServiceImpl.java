package com.ultimetric.usecase.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ultimetric.usecase.model.Customer;
import com.ultimetric.usecase.model.PlaceOrderDetails;
import com.ultimetric.usecase.model.Product;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${BrowseCatalogURL}")
	private String browseCatalogURL;

	@Value("${RegisterCustomerURL}")
	private String registerCustomerURL;

	@Override
	public PlaceOrderDetails placeOrderDetails(int customerId, int productId) {
		PlaceOrderDetails PlaceOrderDetails = new PlaceOrderDetails();
		Customer orderPlacedCustomerDetails = getCustomerDetails(customerId);
		Product productInCart = getPlacedProducts(productId);
		PlaceOrderDetails.setCustomer(orderPlacedCustomerDetails);
		PlaceOrderDetails.setProduct(productInCart);
		PlaceOrderDetails.setPlaceOrderStatus("Success");
		return PlaceOrderDetails;
	}
    
	private Product getPlacedProducts(int productId) {
		return restTemplate.getForObject(browseCatalogURL+productId, Product.class);
	}

	private Customer getCustomerDetails(int customerId) {
		return restTemplate.getForObject(registerCustomerURL+customerId, Customer.class);
	}

}
