package Part3.client.view;


import java.util.List;

import Part3.model.Customer;

/**
 * This class contains the method to retrieve all customers information.
 * The method displayCustomers() will be invoked by client, TCPCustomersClientApp
 * to display the customer list.
 * 
 * @author Ng Wei Hen
 */

public class CustomerViewer {
	
	
	public void displayCustomers(List<Customer> customers) {
		
		// Some information about the record
		System.out.println("\tNumber of record: " + customers.size());
		System.out.println("\tCustomer Information");
		System.out.println("\t-------------------------------------");
		System.out.println("\t ID\t Name");
		
		// Print all customers on console
		for (Customer customer:customers) {
			System.out.print("\t" + customer.getCustomerId());
			System.out.println("\t" + customer.getName());
			
			
		}
		
	}

}
