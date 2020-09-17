package com.ultimetric.usecase.service;

import java.util.List;

import com.ultimetric.usecase.model.Product;

public interface BrowseCatalogService {

	List<Product> getAllProducts();

	List<Product> findProductsBasedCatalog(String catalog);

	List<Product> save(List<Product> list);

}
