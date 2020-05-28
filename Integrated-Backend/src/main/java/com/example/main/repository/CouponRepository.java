package com.example.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.main.model.Coupon;

@Repository("CouponRepository")
public interface CouponRepository extends CrudRepository<Coupon, Integer> {

	public Coupon findByCouponCode(String code);
}
