package com.ultimetric.usecase.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultimetric.usecase.dao.BrowseCatalogDao;
import com.ultimetric.usecase.model.Product;

@Service
public class BrowseCatalogServiceImpl implements BrowseCatalogService {

	@Autowired
	BrowseCatalogDao browseCatalogDao;

	@Override
	public List<Product> save(List<Product> list) {
		return browseCatalogDao.saveProducts(list);
	}

	@Override
	public List<Product> getAllProducts() {
		return browseCatalogDao.findAllProducts();
	}

	@Override
	public List<Product> findProductsBasedCatalog(String catalog) {
		List<Product> dbProductsList = browseCatalogDao.findProductsBasedCatalog(catalog);
		// USECASE 2.a: Remove Duplicate data by product
		Set<Product> set = new LinkedHashSet<Product>(dbProductsList);
		List<Product> nonDuplicatesList = new ArrayList<Product>(set);

		// USECASE 2.b: sort the data by price in ascending by default
		nonDuplicatesList.sort(Comparator.comparing(Product::getPrice));
		nonDuplicatesList.stream().forEach(x -> System.out.println(x.getPrice()));

		// USECASE 2.c: have sorting implemented for productname, price descending
		Comparator<Product> pnameDescComparator = (p1, p2) -> p2.getProductName().compareTo(p1.getProductName());
		Collections.sort(nonDuplicatesList, pnameDescComparator);
		nonDuplicatesList.stream().forEach(x -> System.out.println(x.getProductName()));

		Comparator<Product> priceDescComparator = (p1, p2) -> ((int) p2.getPrice() - (int) p1.getPrice());
		Collections.sort(nonDuplicatesList, priceDescComparator);
		nonDuplicatesList.stream().forEach(x -> System.out.println(x.getPrice()));

		return nonDuplicatesList;
	}

}
