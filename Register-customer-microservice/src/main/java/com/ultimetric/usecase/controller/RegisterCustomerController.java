package com.ultimetric.usecase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ultimetric.usecase.globalExhandler.CustomerNotFoundException;
import com.ultimetric.usecase.model.Customer;
import com.ultimetric.usecase.service.RegisterCustomerService;

@RestController
public class RegisterCustomerController {

	@Autowired
	RegisterCustomerService registerCustomerService;

	@Value("${CustRegNotSuc}")
	private String custRegNotSuc;
	
	@Value("${CusNotFound}")
	private String cusNotFound;
	
	@PostMapping("/saveCustomerDetails")
	private ResponseEntity<Customer> save(@RequestBody Customer cutomerDetails) throws Exception {
		Customer customer =  registerCustomerService.save(cutomerDetails);
		if(customer==null)
	    	throw new Exception();
	    return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}

	@GetMapping("/customers/{customerId}")
	private ResponseEntity<Customer> getBooks(@PathVariable("customerId") int customerId) {
		Customer customer = registerCustomerService.findCustomerById(customerId);
		if(customer==null)
	    	throw new CustomerNotFoundException(cusNotFound);
	    return new ResponseEntity<Customer>(customer,HttpStatus.FOUND);
	}

}

//http://localhost:8081/customers/2
//http://localhost:8081/saveCustomerDetails
// {"id":2, "customerName":"Karthik","mobileNumber":7373951233,"address":"chennai","paymentMode":"cash on delivery"}