package com.example.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.model.MerchantDetails;

@Repository("MerchantRepository")
public interface MerchantRepository extends JpaRepository<MerchantDetails, Integer> {

	MerchantDetails findByName(String name);

	MerchantDetails findByEmail(String eMail);

}
