package com.ultimetric.usecase.dao;

import java.util.List;

import com.ultimetric.usecase.model.Product;

public interface BrowseCatalogDao {

	List<Product> saveProducts(List<Product> list);

	List<Product> findAllProducts();

	List<Product> findProductsBasedCatalog(String catalog);

}
