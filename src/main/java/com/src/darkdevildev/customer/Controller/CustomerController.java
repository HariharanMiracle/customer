package com.src.darkdevildev.customer.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.src.darkdevildev.customer.model.Customer;
import com.src.darkdevildev.customer.repository.CustomerRepository;

@RestController
@RequestMapping(value="api")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "G://Hariharan//work//test1//public//uploads//customer//";
	
	
//	http://localhost:9900/api/customer
	@CrossOrigin
	@GetMapping(value="customer")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		System.out.println("getAllCustomers");
		System.out.println("request data: none");
		return customerRepository.findAll();
	}
	
//	http://localhost:9900/api/customer/1
	@CrossOrigin
	@GetMapping(value="customer/{customerId}")
	public @ResponseBody Optional<Customer> getCustomerById(@PathVariable int customerId) {
		System.out.println("getCustomerById");
		System.out.println("request data: " + customerId);
		return customerRepository.findById(customerId);
	}
	
//	http://localhost:9900/api/customer/name/Yasikaran
	@CrossOrigin
	@GetMapping(value="customer/name/{customerName}")
	public @ResponseBody Customer getCustomerByCustomerName(@PathVariable String customerName){
		System.out.println("getCustomerByCustomerName");
		System.out.println("request data: " + customerName);
		return customerRepository.findByCustomerName(customerName);
	}
	
//	http://localhost:9900/api/customer/name/like/an
	@CrossOrigin
	@GetMapping(value="customer/name/like/{customerName}")
	public @ResponseBody Iterable<Customer> getCustomersByCustomerNameLike(@PathVariable String customerName){
		System.out.println("getCustomersByCustomerNameLike");
		System.out.println("request data: " + customerName);
		return customerRepository.findByCustomerNameLike(customerName);
	}
	
//	POST
//	http://localhost:9900/api/customer
	@CrossOrigin
	@PostMapping(value="customer")
	public @ResponseBody Customer saveCustomer(@RequestParam("customerName") String customerName, @RequestParam("customerPicture") MultipartFile file){
		System.out.println("saveCustomer");
		System.out.println("request data: " + customerName);
		
		Customer customer = new Customer();
		customer.setCustomerName(customerName);
		
		if (file.isEmpty()) {
			System.out.println("No customer picture uploaded");
			return new Customer();
        }
		else {
			try {
	        	int leftLimit = 97; // letter 'a'
	            int rightLimit = 122; // letter 'z'
	            int targetStringLength = 6;
	            Random random = new Random();
	            StringBuilder buffer = new StringBuilder(targetStringLength);
	            for (int i = 0; i < targetStringLength; i++) {
	                int randomLimitedInt = leftLimit + (int) 
	                  (random.nextFloat() * (rightLimit - leftLimit + 1));
	                buffer.append((char) randomLimitedInt);
	            }
	            String generatedString = buffer.toString();
	         
	            System.out.println(generatedString);
	            
	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            String file_name = generatedString + "_" + file.getOriginalFilename();
	            Path path = Paths.get(UPLOADED_FOLDER + file_name);
	            Files.write(path, bytes);

	            customer.setCustomerPicture(file_name);
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Un-expected error");
	            return new Customer();
	        }
		}
		
		return customerRepository.save(customer);
	}
	
//	DELETE
//	http://localhost:9900/api/customer/1
	@CrossOrigin
	@DeleteMapping(value="customer/{customerId}")
	public boolean deleteCustomer(@PathVariable int customerId){
		System.out.println("deleteCustomer");
		System.out.println("request data: " + customerId);
		
		if(!customerRepository.findById(customerId).isPresent()) {
			System.out.println("No record to delete");
			return false;
		}
		else {
			System.out.println("Record deleted");
			Customer customer = customerRepository.findById(customerId).get();
			customerRepository.delete(customer);
			return true;
		}
	}

//	PUT
//	http://localhost:9900/api/customer	
//	{
//		"customerId": 1,
//	    "customerName": "Daraniya",
//	    "customerPicture": "picture4.JPG"
//	}
	@CrossOrigin
	@PutMapping(value="customer", consumes = "application/json", produces = "application/json")
	public @ResponseBody Customer updateCustomer(@RequestBody Customer customer){
		System.out.println("updateCustomer");
		System.out.println("request data: " + customer.toString());
		return customerRepository.save(customer);
	}
}
