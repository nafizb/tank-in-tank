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
    	//New client come up.
    	this.socket = socket;
     
    }
    
	@Override
	public void run() {
		System.out.println("New client's thread is running up.");
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
				receivedData(response);
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	public void receivedData(String response) {
		 if(nick == null) {
			 nick = response;
		 }else if(response.equals("*createRoom*")) {
			 createRoom();
		 }else if(response.equals("*roomList*")) {
			 roomList();
		 }else if(response.startsWith("*joinRoom")) {
			 joinRoom(response);
		 }
	}
	public void createRoom() {
		 server.rooms.add(new Room(nick,ip));
		 server.roomCounter++;
	}
	
	public void roomList() {
		 Iterator<Room> it = server.rooms.iterator();
		 String roomList = "";
		 
		 int i = 0;
		 while(it.hasNext()) {
			 Room room = it.next();
			 
			 //036 and 037 are ascii characters which haven't output.
			 if(i != 0 )
				 roomList += (char)036;
			 
			 roomList += room.name +(char)037+room.ID;
			 i++;
		 }
		 
		 output.println(roomList);
	}
	
	public void joinRoom(String response) {
		String[] parseResponse = response.split(":");
		int roomID = Integer.valueOf(parseResponse[1]);
		
		//Check the room for availability
		output.println("*granted:"+Room.getIpById(roomID));
	
	}
	

}
