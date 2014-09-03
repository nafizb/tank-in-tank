package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import main.game;

public class MainConnection extends Thread{
	
	public String serverIP;
	
	public int status = 0;
	
	Socket clientSocket;
	
    BufferedReader input = null;
    PrintStream output = null;
    
    boolean connect = false;
    
    public Collection<Room> roomList = new ArrayList<Room>();
    
   // public Collection<Clients>
	public MainConnection() {
		
	}
	
	public void connect(String ip) {
		serverIP = ip;
		connect = true;
	}
	
	@Override 
	public void run() {
		

		while(true) {
			try {
				sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			if(!connect) continue;
			
			status = 1;
			game.connectionStatus = status;
			
			try {
				clientSocket = new Socket(serverIP, game.port);
				input = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));
				output = new PrintStream(clientSocket.getOutputStream());

			} catch (UnknownHostException e) {
				System.err.println("Unknown host: " + serverIP);
				status = 100;
			} catch (IOException e) {
				System.err.println(serverIP + " disconnected");
				status = 101;
			}

			if (status >= 100) {
				System.out.println("Connection problem.");
				connect = false;
				game.connectionStatus = status;

			} else {
				status = 2;
				game.connectionStatus = status;
				
				break;
			}
			
			
		}

		//main process
		
		output.println(game.nick);
		getRoomList();
		
	}
	public void createRoom() {
		output.println("*createRoom*");
	}
	public void getRoomList() {
		roomList.removeAll(roomList);

		output.println("*roomList*");
		try {
			String list = input.readLine();
			//for(String item:list.split("\\|\\|"))
			for(String item:list.split(String.valueOf(Character.toChars(036)))) {
				if(item.equals("")) continue;
				
				String[] roomString = item.split(String.valueOf(Character.toChars(037)));
				
				roomList.add(new Room(roomString[0], Integer.valueOf(roomString[1])));
			}
				
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectRoom(int roomID) {
		output.println("*joinRoom:"+roomID);
		String response = readLine();
		
		System.out.println("Room IP: "+response);
	}

	public String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
