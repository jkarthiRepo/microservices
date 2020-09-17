package com.ultimetric.usecase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ultimetric.usecase.model.Product;
import com.ultimetric.usecase.service.BrowseCatalogService;

@RestController
public class BrowseCatalogController {
	@Autowired
	BrowseCatalogService browseCatalogService;

	//USECASE 1: API to search products based catalog
	@GetMapping("/products/{catalogDescription}")
	private List<Product> getBooks(@PathVariable("catalogDescription") String catalogDescription) {
		return browseCatalogService.findProductsBasedCatalog(catalogDescription);
	}

	@PostMapping("/save")
	private List<Product> save(@RequestBody List<Product> products) {
		products.stream().forEach(p->System.out.println(p.getCatalogDescription()));
		browseCatalogService.save(products);
		return products;
	}

	@GetMapping("/products")
	private List<Product> getAllProducts() {
		return browseCatalogService.getAllProducts();
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