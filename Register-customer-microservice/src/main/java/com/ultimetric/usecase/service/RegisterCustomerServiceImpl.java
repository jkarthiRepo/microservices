package com.ultimetric.usecase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultimetric.usecase.dao.RegisterCustomerDao;
import com.ultimetric.usecase.model.Customer;

@Service
public class RegisterCustomerServiceImpl implements RegisterCustomerService {

	@Autowired
	RegisterCustomerDao registerCustomerDao;

	@Override
	public Customer save(Customer customer) {
		return registerCustomerDao.save(customer);
	}

	@Override
	public Customer findCustomerById(int ustomerId) {
		return registerCustomerDao.findCustomerById(ustomerId);
	}

}
