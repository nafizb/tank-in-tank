package controls;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import network.Room;
import screen.Menu;
import main.game;
import entities.tank;

public class Keyboard {

	public static String bufferString = new String(); 
	boolean pressedShift = false;
	
	public void keyPressed(KeyEvent e) {
		if(game.isConnected) {
			onGame(e);
		} else {
			onMenu(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		game.keys[key] = false;
	}
	
	public void onGame(KeyEvent e) {
		int key = e.getKeyCode();
		game.keys[key] = true;
		if (game.keys[32]) {
			//Space fires.
			tank tan = (tank) game.entities.get(0);
			tan.fire();
		}		
	}
	
	public void onMenu(KeyEvent e) {

		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		
		//System.out.println(keyCode);
		
		if (keyCode == 8) {
			//Backspace deletes char, what a suprise.
			if (bufferString.length() > 0)
				bufferString = bufferString.substring(0,
						bufferString.length() - 1);
		} else if (keyCode == 16) {
			//Shift has a same function with Tab. Deletes buffered string because there's no cursor.
			pressedShift = !pressedShift;
			bufferString = "";
		} else if (keyCode == 10) {
			//Enter provide confirm entered information.
			if (game.connectionStatus == 0) {
				//If there is no connection
				if (!game.serverIP.equals("") && !game.nick.equals("")) {
					game.network.connect(game.serverIP);
				}
			} else if (game.connectionStatus == 2) {
				//Has a server connection to jump a room.
				System.out.println("Connect room");
				
				ArrayList<Room> roomList = (ArrayList<Room>) game.network.roomList;
				
				int selectedRoomID = roomList.get(game.menuScreen.roomSelectionPos-1).ID;
				game.network.connectRoom(selectedRoomID);
				
			}

		} else if (keyCode == 67) {
			//key c creates room.
			if (game.connectionStatus == 2) {
				game.network.createRoom();
				game.isConnected = true;
			}
		} else if (keyCode == 116) {
			//key f5 of course refresh room list.
			if (game.connectionStatus == 2) {
				game.network.getRoomList();
			}
		} else if (keyCode == 38) {
			//arrow up
			if (game.connectionStatus == 2) {
				 game.menuScreen.roomSelectionPos--;
			}
		} else if (keyCode == 40) {
			//arrow down
			if (game.connectionStatus == 2) {
				game.menuScreen.roomSelectionPos++;
			}
		} else {
			//other keys are buffered in bufferString to fill up input boxes.
			bufferString += keyChar;
		}

		if (!pressedShift)
			game.serverIP = bufferString;
		else
			game.nick = bufferString;
	}
	}
 