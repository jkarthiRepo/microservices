package com.ultimetric.usecase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ultimetric.usecase.globalExhandler.ProductNotFoundException;
import com.ultimetric.usecase.model.Product;
import com.ultimetric.usecase.service.BrowseCatalogService;

@RestController
public class BrowseCatalogController {
	@Autowired
	BrowseCatalogService browseCatalogService;

	@Value("${ProductNotFound}")
	private String productNotFound;
	
	@Value("${ProductNotSaved}")
	private String productNotSaved;
	
	//USECASE 1: API to search products based catalog
	@GetMapping("/products/{catalogDescription}")
	private ResponseEntity<List<Product>> getBooks(@PathVariable("catalogDescription") String catalogDescription) {
		List<Product> productsList =  browseCatalogService.findProductsBasedCatalog(catalogDescription);
	    if(productsList.size()<=0)
	    	throw new ProductNotFoundException(productNotFound);
	    return new ResponseEntity<List<Product>>(productsList,HttpStatus.FOUND);
	}

	@PostMapping("/save")
	private ResponseEntity<List<Product>> save(@RequestBody List<Product> products) throws Exception {
		products.stream().forEach(p->System.out.println(p.getCatalogDescription()));
		List<Product> productsList =  browseCatalogService.save(products);
		if(productsList.size()<=0)
	    	throw new Exception(productNotSaved);
	    return new ResponseEntity<List<Product>>(productsList,HttpStatus.OK);
	}

	@GetMapping("/getProductDetails/{productId}")
	private ResponseEntity<Product> getProductDetails(@PathVariable int productId) {
		Product product = browseCatalogService.getProductDetails(productId);
		if(product==null)
	    	throw new ProductNotFoundException(productNotFound);
	    return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}

}




//[
// {"id":1, "productName":"Apple","price":30000,"catalogDescription":"mobile"},
// {"id":2, "productName":"Samsung","price":10000,"catalogDescription":"mobile"},
// {"id":3, "productName":"Oneplus","price":20000,"catalogDescription":"mobile"},
//  {"id":4, "productName":"Onida","price":60000,"catalogDescription":"TV"},
// {"id":5, "productName":"Samsung","price":50000,"catalogDescription":"TV"},
// {"id":6, "productName":"MI Android","price":70000,"catalogDescription":"TV"},
//  {"id":7, "productName":"Hier","price":40000,"catalogDescription":"Refrigirator"},
// {"id":8, "productName":"Whirlpool","price":30000,"catalogDescription":"Refrigirator"},
// {"id":9, "productName":"Mitashi","price":50000,"catalogDescription":"Refrigirator"},
// {"id":10, "productName":"Apple","price":30000,"catalogDescription":"mobile"}
// 
// ]