package com.example.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.model.Product;

@Repository(value="ProductRepository")
public interface ProductRepository extends JpaRepository<Product,Integer> {


	Product findByProductId(int productId);

	List<Product> findByProductCategory(String productCategory);

	List<Product> findByProductBrand(String productBrand);

	List<Product> findByProductInfo(String productInfo);

	List<Product> findAllByProductCategory(String productCategory);

	

}
