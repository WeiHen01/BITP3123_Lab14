package Part3.server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import Part3.model.Customer;
import Part3.server.controller.CustomerDataManager;

/**
 * Lab Week 14 Part 3 Exercise 9
 * This TCP server class will return a list of customers to the client.
 * 
 * @author Ng Wei Hen
 */

public class TCPCustomersServerApp {

	public static void main(String[] args) {
		
		// declare a port number
		int portNo = 8088;
		
		// declare an object for controller class
		CustomerDataManager manager = new CustomerDataManager();
		
		System.out.println("\n\tExecuting TCP Customers Server App");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
								
				// Get all customers
				List<Customer> customers = manager.getCustomerList();
				
				// 4. Respond to client
				OutputStream osOutput  = clientSocket.getOutputStream();
				ObjectOutputStream oosOutput = new ObjectOutputStream(osOutput);
				oosOutput.writeObject(customers);
				System.out.println("\tSending : " + customers.size() 
					+ " customers");
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}

}
