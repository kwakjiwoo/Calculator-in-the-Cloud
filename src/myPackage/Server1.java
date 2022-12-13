package myPackage;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

	public static void main(String[] args) throws Exception {
		String clientSentence = null; 
		String[] calCommand;
		int num1, num2;
		String calResult = null;
		
		ServerSocket welcomeSocket = new ServerSocket(6789); //create welcoming socket
		
		System.out.println("type ADD/MINUS/MUL/DIV and two numbers to calculate\n ");
		
		while(true) { 
			Socket connectionSocket = welcomeSocket.accept(); //wait on welcoming socket for client
			
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream())); //communicate with client
					
			DataOutputStream outToClient = 
					new DataOutputStream(connectionSocket.getOutputStream()); //communicate with client
			
			clientSentence = inFromClient.readLine(); 
			System.out.println("CLIENT: " + clientSentence); //print client's input
			
			calCommand = clientSentence.split(" "); //split calculate command and numbers
			
			if(calCommand.length>3) 
				calResult = "Error:\n Too many arguments"; //error message when client input has more than 2 number
			
			else if(calCommand.length<3)
				calResult = "Error:\n Not enough argumants"; //error message when client input has less than 2 number
			
			else{
				num1 = Integer.parseInt(calCommand[1]); 
				num2 = Integer.parseInt(calCommand[2]);
				
				if(calCommand[0]!="ADD" && calCommand[0]!="MINUS" && calCommand[0]!="MUL" && calCommand[0]!="DIV") 
					calResult = "Error:\n Wrong command"; //error message when client input has wrong calculate command
				if(calCommand[0].equals("DIV")) {
					if(num2==0) calResult = "Error:\n Divided by zero"; //error message when divided by zero
					else calResult = Integer.toString(num1/num2);
				}
				if(calCommand[0].equals("ADD")) 
					calResult = Integer.toString(num1+num2);
				if(calCommand[0].equals("MINUS")) 
					calResult = Integer.toString(num1-num2);
				if(calCommand[0].equals("MUL")) 
					calResult = Integer.toString(num1*num2);
				
			}
			
			((DataOutput) outToClient).writeBytes(calResult);
			
			System.out.println("RESULT: " + calResult); //print the result of calculation
		
			
		}

	}

}
