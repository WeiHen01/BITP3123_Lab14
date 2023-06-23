package Part2Exercise2.server;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Part2Exercise2.controller.SentenceTextProcessor;

/**
 * Lab Week 14 Part 2 Exercise 2
 * This server class will using UDP Stream to process
 * and calculate number of vowels, consonants and punctuations
 * in a sentence that received from client.
 * 
 * Then it will send the results back to the client.
 * 
 * @author Ng Wei Hen
 * 
 */
public class UDPServerSentenceApp {

public static void main(String[] args) {
		
		System.out.println("UDP Server Sentence App: UDP Sentence Processing "
				+ "Server-Side Application.");
		
		
		// Permissible port for this application
		int portNo = 8083;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to received data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object represents packet from client
                DatagramPacket receivedPacket = 
                		new DatagramPacket(receivedData, receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceTextProcessor processor = 
						new SentenceTextProcessor(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".\n");
				
				// This is not used because it give a misleading result
	            // int length = sentence.length();
	            
	            // More processing
	            int totalVowel = processor.CountVowel();
	            int totalConso = processor.CountConso();
	            int totalPunct = processor.CountPunct();
	            
	            byte[] outData = processor.convertToByteArray(totalVowel);
	            byte[] outData1 = processor.convertToByteArray(totalConso);
	            byte[] outData2 = processor.convertToByteArray(totalPunct);
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            int sizeOutData = outData.length;
	            int sizeOutData1 = outData1.length;
	            int sizeOutData2 = outData2.length;
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket outPacket = new DatagramPacket(outData, 
	            		sizeOutData, clientAddress, clientPort);

	            DatagramPacket outPacket1 = new DatagramPacket(outData1, 
	            		sizeOutData1, clientAddress, clientPort);

	            DatagramPacket outPacket2 = new DatagramPacket(outData2, 
	            		sizeOutData2, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(outPacket);
	            System.out.println("Message sent (total Vowel) : " 
	            		+ totalVowel  + ".\n");
	            
	            datagramSocket.send(outPacket1);
	            System.out.println("Message sent (total Consonant) : " 
	            		+ totalConso  + ".\n");
	            
	            datagramSocket.send(outPacket2);
	            System.out.println("Message sent (total Punctuation) : " 
	            		+ totalPunct  + ".\n");
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDP Server Sentence App: End of program.");
    }
}
