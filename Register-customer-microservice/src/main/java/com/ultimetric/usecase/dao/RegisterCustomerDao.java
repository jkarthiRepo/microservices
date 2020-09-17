package com.ultimetric.usecase.dao;

import com.ultimetric.usecase.model.Customer;

public interface RegisterCustomerDao {

	Customer save(Customer customer);

	Customer findCustomerById(int ustomerId);

}
