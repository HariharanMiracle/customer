package com.src.darkdevildev.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.src.darkdevildev.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findByCustomerName(String customerName);
	
	@Query("select c from Customer c where c.customerName like %?1%")
	public Iterable<Customer> findByCustomerNameLike(String customerName);
}
