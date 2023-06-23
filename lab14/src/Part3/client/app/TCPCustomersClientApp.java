package Part3.client.app;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import Part3.client.view.CustomerViewer;
import Part3.model.Customer;

/**
 * Lab Week 14 Part 3 Exercise 10
 * This is a TCP client class will receive a list of customers from the server.
 * @author Ng Wei Hen
 *
 */
public class TCPCustomersClientApp {
	
	public static void main (String[] args) {
		
		try {
		
			// Server information
			int serverPortNo = 8088;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			
			// Create stream to receive response from the server
			InputStream isInput = socket.getInputStream();
			ObjectInputStream oisInput = new ObjectInputStream(isInput);
			
			// 3. Read respond from the server - cast the object
			List<Customer> customers = (List<Customer>) oisInput.readObject();
			
			// 4. Process response - display the object
			CustomerViewer customerView = new CustomerViewer();
			customerView.displayCustomers(customers);
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		
		
		
		
		
	}

}
