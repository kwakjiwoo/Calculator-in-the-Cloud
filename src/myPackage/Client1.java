package myPackage;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client1 {

	public static void main(String[] args) throws Exception {
		String calculate; 
		String result; 
		
		BufferedReader inFromUser = 
		new BufferedReader(new InputStreamReader(System.in)); //communicate with Server
		
		Socket clientSocket = new Socket("127.0.0.1", 6789); //create client socket
		
		DataOutputStream outToServer = 
		new DataOutputStream(clientSocket.getOutputStream()); //communicate with Server
		BufferedReader inFromServer = new BufferedReader
				(new InputStreamReader(clientSocket.getInputStream())); //communicate with Server
		
		calculate = inFromUser.readLine(); //store input 
		
		((DataOutput) outToServer).writeBytes(calculate + '\n'); //send input to server
		
		result = inFromServer.readLine(); 
		System.out.println(result); //print calculate result
		
		clientSocket.close(); //close client socket
	}
	
}
