package com.ultimetric.usecase.service;

import com.ultimetric.usecase.model.PlaceOrderDetails;

public interface PlaceOrderService {

	PlaceOrderDetails placeOrderDetails(int customerId, int productId);

}
