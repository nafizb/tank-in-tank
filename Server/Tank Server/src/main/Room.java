package main;

import java.util.ArrayList;
import java.util.Collection;

public class Room {
	
	int ID = -1;
	String name;
	int playerCount = 1;

	String IP;
	//Collection<String> playersName = new ArrayList<String>();
	//Collection<String> playersIP = new ArrayList<String>();

	public Room(String creatorName, String creatorIP) {
		name = creatorName+"'s Room";
		IP = creatorIP;
		//this.playersName.add(creatorName);
		//this.playersIP.add(creatorIP);
		ID = server.roomCounter;
		System.out.println("New room is created./"+ creatorName + "/creatorIP/"+creatorIP);
	}
	
	static String getIpById(int roomID) {
		for (Room temp : server.rooms) {
			if(temp.ID == roomID) {
				return temp.IP;
			}
		}
		return "";
	}
}
