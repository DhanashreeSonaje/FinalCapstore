package com.example.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.model.ProductFeedback;

@Repository("ProductFeedbackRepository")
public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, Integer> {

	void deleteByUserId(int userId);

	

}
