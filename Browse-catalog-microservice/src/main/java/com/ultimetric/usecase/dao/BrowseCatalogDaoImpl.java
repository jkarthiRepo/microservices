package com.ultimetric.usecase.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ultimetric.usecase.model.Product;
import com.ultimetric.usecase.repository.BrowseCatalogRepository;

@Repository
public class BrowseCatalogDaoImpl implements BrowseCatalogDao {

	@Autowired
	EntityManager manager;

	@Autowired
	BrowseCatalogRepository browseCatalogRepository;

	@Override
	public List<Product> saveProducts(List<Product> list) {
		return (List<Product>) browseCatalogRepository.saveAll(list);
	}

	@Override
	public List<Product> findProductsBasedCatalog(String catalog) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> root = cq.from(Product.class);
		Predicate predicate = cb.equal(root.get("catalogDescription"), catalog);
		List<Product> products = manager.createQuery(cq.select(root).where(predicate)).getResultList();
		return products;
	}

	@Override
	public Product getProductDetails(int productId) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> root = cq.from(Product.class);
		Predicate predicate = cb.equal(root.get("id"), productId);
		List<Product> products = manager.createQuery(cq.select(root).where(predicate)).getResultList();
		if(products.size()>0) {
			return products.get(0);
		}
		return null;
	}

}
