package controls;

import java.awt.event.KeyEvent;

import screen.Menu;

import main.game;

import entities.tank;

public class Keyboard {

	public static String bufferString = new String(); 
	boolean pressedShift = false;
	public void keyPressed(KeyEvent e) {
		if(game.isConnected) {
			int key = e.getKeyCode();
			game.keys[key] = true;
			if (game.keys[32]) {
				tank tan = (tank) game.entities.get(0);
				tan.fire();
			}
		} else {
			//System.out.println(e.getKeyCode());
			
			if (e.getKeyCode() == 8) {
				if (bufferString.length() > 0)
					bufferString = bufferString.substring(0,
							bufferString.length() - 1);
			} else if (e.getKeyCode() == 16) {
				pressedShift = !pressedShift;
				bufferString = "";
			} else if (e.getKeyCode() == 10) {
				if(game.connectionStatus == 0) {
					if(!game.serverIP.equals("") && !game.nick.equals("")) {
						game.network.connect(game.serverIP);
					}
				}
				else if(game.connectionStatus == 2) {
					System.out.println("Connect room");
				}
				
			} else if(e.getKeyCode() == 67 ) {
				if(game.connectionStatus == 2){
					game.network.createRoom();
					game.isConnected = true;
				}
			} else if(e.getKeyCode() == 116) {
				if(game.connectionStatus == 2){
					game.network.getRoomList();
				}
			} else if (e.getKeyCode() == 38) {
				if(game.connectionStatus == 2) {
					((Menu) game.menuScreen).roomSelectionPos--;
				}
			} else if (e.getKeyCode() == 40) {
				if(game.connectionStatus == 2) {
					((Menu) game.menuScreen).roomSelectionPos++;
				}
			} 
			else {
				bufferString += e.getKeyChar();
			}
			
			if(!pressedShift) 
				game.serverIP = bufferString;
			else 
				game.nick = bufferString;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		game.keys[key] = false;
	}

}
