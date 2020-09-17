package com.ultimetric.usecase.service;

import com.ultimetric.usecase.model.Customer;

public interface RegisterCustomerService {

	Customer save(Customer customer);

	Customer findCustomerById(int ustomerId);

}
