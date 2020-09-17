package com.ultimetric.usecase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ultimetric.usecase.globalExhandler.OrderNotPlacedException;
import com.ultimetric.usecase.model.PlaceOrderDetails;
import com.ultimetric.usecase.service.PlaceOrderService;

@RestController
public class PlaceOrderController {

	@Autowired
	PlaceOrderService placeOrderService;

	@Value("${OrderNotPlaced}")
	private String orderNotPlaced;
	
	@GetMapping("/placeOrder")
	private ResponseEntity<PlaceOrderDetails> placeOrder(@RequestParam int customerId, @RequestParam int productId) {
		PlaceOrderDetails placeOrderDetails = placeOrderService.placeOrderDetails(customerId, productId);
		if (placeOrderDetails == null)
			throw new OrderNotPlacedException(orderNotPlaced);
		return new ResponseEntity<PlaceOrderDetails>(placeOrderDetails, HttpStatus.OK);
	}

}
