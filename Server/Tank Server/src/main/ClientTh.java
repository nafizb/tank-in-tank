package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;

public class ClientTh extends Thread{

    BufferedReader input;
    PrintStream output;
    Socket socket;
    
    boolean connected = true;
    
    String nick;
    String ip;
    
    ClientTh(Socket socket) {
    	System.out.println("Hoþ geldin paþam");
    	this.socket = socket;
     
    }
    
	@Override
	public void run() {
		System.out.println("Baðlandým horay");
		   try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    output = new PrintStream(socket.getOutputStream());
			    
			    ip = socket.getInetAddress().getHostAddress(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		String response = new String();
		
		try {
			while ((response = input.readLine()) != null) {   
							
					 if(nick == null) {
						 nick = response;
					 
						 
						 
					 }
					 if(response.equals("*createRoom*")) {
						 server.rooms.add(new Room(nick,ip));
					 }else if(response.equals("*roomList*")) {
						 Iterator<Room> it = server.rooms.iterator();
						 String roomList = "";
						 
						 int i = 0;
						 while(it.hasNext()) {
							 String name = it.next().roomName;
							 if(i != 0 )
								 roomList += "||";
							 roomList += name;
							 i++;
						 }
						 
						 output.println(roomList);
					 }
					 
				
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	

}
