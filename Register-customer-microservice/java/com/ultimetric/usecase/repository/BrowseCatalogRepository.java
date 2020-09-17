package com.ultimetric.usecase.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ultimetric.usecase.model.Product;  

@Repository
public interface BrowseCatalogRepository extends CrudRepository<Product, String>  {

}
