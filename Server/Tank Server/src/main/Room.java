package main;

import java.util.ArrayList;
import java.util.Collection;

public class Room {

	String roomName;
	int playerCount = 1;
	Collection<String> playersName = new ArrayList<String>();
	Collection<String> playersIP = new ArrayList<String>();
	
	public Room(String creatorName, String creatorIP) {
		this.roomName = creatorName+"'s Room";
		this.playersName.add(creatorName);
		this.playersIP.add(creatorIP);
		System.out.println("Oda kuruldu/"+ creatorName + "/creatorIP/"+creatorIP);
	}
}
