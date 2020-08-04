package com.src.darkdevildev.customer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	private String customerName;
	private String customerPicture;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String customerName, String customerPicture) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPicture = customerPicture;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPicture() {
		return customerPicture;
	}

	public void setCustomerPicture(String customerPicture) {
		this.customerPicture = customerPicture;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPicture="
				+ customerPicture + "]";
	}
	
}
