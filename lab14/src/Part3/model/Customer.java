package Part3.model;

import java.io.Serializable;

/**
 * Lab Week 14 Part 3 Exercise 2
 * This class represent a customer.
 * 
 * @author Ng Wei Hen
 */
public class Customer implements Serializable{

	private int customerId;
	private String name;
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
