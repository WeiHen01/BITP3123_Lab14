package Part3.client.app;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import Part3.model.Customer;

/**
 * Lab Week 14 Part 3 Exercise 7
 * This TCP client class will send several customer id to the server.
 * The application will also receive a customer object from the server.
 * 
 * @author Ng Wei Hen
 *
 */
public class TCPCustomersByIdClientApp {
	
	public static void main (String[] args) {
		
		try {
			
			System.out.println("\tExecuting TCP Customer By ID Server App");
		
			// Server information
			int serverPortNo = 8088;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			// Create stream to send request
			OutputStream osOutput = socket.getOutputStream();
			DataOutputStream dosOutput = new DataOutputStream(osOutput);
			
			// 2. Send request to the server
			// Input the customer id 
			System.out.print("Enter customer id: ");
			Scanner sc = new Scanner(System.in);
			int customerId = sc.nextInt();
			
			// write the input to the network socket
			dosOutput.writeInt(customerId);
			System.out.println("\tRequesting customer id " + customerId + "\n");
			
			// Create stream to receive response from the server
			InputStream isInput = socket.getInputStream();
			ObjectInputStream oisInput = new ObjectInputStream(isInput);
			
			
			// 3. Read respond from the server - cast object
			Customer customer = (Customer) oisInput.readObject();
			
			// 4. Process response - display the object
			System.out.println("\tCustomer Information (From the server)");
			System.out.println("\tCustomer Id: " + customer.getCustomerId());
			System.out.println("\tName: " + customer.getName());
			
			
		} catch (Exception ex) {
			
			
			
		}
		
		
		
		
		
		
	}

}
