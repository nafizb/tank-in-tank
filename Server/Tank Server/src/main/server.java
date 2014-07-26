package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;

public class server {

	int port = 7283;
	
    ServerSocket serverSocket;
    ClientTh clientSocket = null;

    Collection<ClientTh> clients = new ArrayList<ClientTh>();
    public static Collection<Room> rooms = new ArrayList<Room>();
    
	server() {
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Server's running...");
		while(true){
		    try {
		    	
		    	clientSocket = new ClientTh(serverSocket.accept());
		    	clientSocket.start();
		    	
		    	clients.add(clientSocket);
		    	
			}
		    catch (IOException e) {System.out.println(e);}
		}
		
		
	}
}
