package com.ultimetric.usecase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ultimetric.usecase.model.Customer;
import com.ultimetric.usecase.repository.RegisterCustomerRepository;

@Repository
public class RegisterCustomerDaoImpl implements RegisterCustomerDao {

	@Autowired
	RegisterCustomerRepository registerCustomerRepository;

	@Override
	public Customer save(Customer customer) {
		return registerCustomerRepository.save(customer);
	}

	@Override
	public Customer findCustomerById(int customerId) {
		return registerCustomerRepository.findById(customerId).get();
	}

}
