package com.example.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.main.model.Product;

@Repository(value="ProductRepository")
public interface ProductRepository extends JpaRepository<Product,Integer> {


	List<Product> findAllByProductCategory(String productCategory);

	boolean existsByProductCategory(String productCategory);

	Product findByProductId(int productId);

	List<Product> findByProductCategory(String productCategory);

	List<Product> findByProductBrand(String productBrand);

	List<Product> findByProductInfo(String productInfo);
	
	@Query(value = "SELECT p.user_id FROM products p WHERE p.product_id = :pid", nativeQuery=true)
	public int findMerchantId(@Param("pid") int productId);

}
