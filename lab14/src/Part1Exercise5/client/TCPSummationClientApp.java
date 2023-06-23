package Part1Exercise5.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Lab Week 14 Part 1 Exercise 5
 * This class is to send 3 numbers to the server 
 * and received two results (sum and product) from the server
 * 
 * @author: Ng Wei Hen
 */
public class TCPSummationClientApp {

	public static void main(String [] args)
	{
		// construct object from java.util.Scanner
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\t Executing TCP Summation Client App");
		
		// receive input for three numbers at client-side
		System.out.print("\tEnter first number: ");
		int number1 = sc.nextInt();
		
		System.out.print("\tEnter second number: ");
		int number2 = sc.nextInt();
		
		System.out.print("\tEnter third number: ");
		int number3 = sc.nextInt();
		
		try {
			
			// 1. Define server information
			// Connect to the server with the port number 8087
			int serverPortNo = 8087;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 2. Bind to the server, request for connection
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			// 3. Send data to the server
			OutputStream outStream = socket.getOutputStream();
			DataOutputStream dosOutput = new DataOutputStream(outStream);
			
			// write the input to the stream for sending request
			dosOutput.writeInt(number1);
			dosOutput.writeInt(number2);
			dosOutput.writeInt(number3);
			
			// 4. Process response from the server
			InputStream inStream = socket.getInputStream();
			DataInputStream disInput = new DataInputStream(inStream);
			
			// read the data retrieved from the server
			// result1 = result of sum, result2 = result of product (multiplication)
			int result1 = disInput.readInt();
			int result2 = disInput.readInt();
			
			// 5. Further processing - display the result
			System.out.println("\tSending to the server: " + number1 + ", " 
					+ number2 + ", " + number3);
			System.out.println("\tReceiving from server: ");
			System.out.println("\tSum: " + result1);
			System.out.println("\tProduct: " + result2);
			
			// Close all the closable
			disInput.close();
			dosOutput.close();
			socket.close();
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		// indication for end execution
		System.out.println("\tEnd of execution at TCPSummationClientApp");
		
	}
}
