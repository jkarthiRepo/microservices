package com.ultimetric.usecase.globalExhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotPlacedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotPlacedException(String exception) {
		super(exception);
	}

}