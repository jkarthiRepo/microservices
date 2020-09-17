package com.ultimetric.usecase.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ultimetric.usecase.model.Customer;  

@Repository
public interface RegisterCustomerRepository extends CrudRepository<Customer, Integer>  {

}
