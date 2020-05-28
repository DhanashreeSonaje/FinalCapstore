package com.example.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.main.model.CommonFeedback;

@Repository("CommonFeedbackRepository")
public interface CommonFeedbackRepository extends JpaRepository<CommonFeedback, Integer> {

	void deleteByUserId(int userId);

	List<CommonFeedback> findAllByUserId(int userId);

	List<CommonFeedback> findAllByProductId(int productId);

}
