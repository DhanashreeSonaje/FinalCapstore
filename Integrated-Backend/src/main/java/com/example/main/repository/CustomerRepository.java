package com.example.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.model.CustomerDetails;

@Repository("CustomerRepository")
public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer>{

	CustomerDetails findByName(String name);

	CustomerDetails findByEmail(String eMail);

	boolean existsByEmail(String eMail);

}
