package screen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Iterator;

import entities.Entity;

import main.game;

public class Menu extends screenInterface{
	
	public int roomSelectionPos = 1;

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(0x00FF44));
		g.fillRect(0, 0, 800, 600);
		
		if(game.connectionStatus == 2)
			roomScreen(g);
		else 
			connectScreen(g);
	}
	public void roomScreen(Graphics2D g) {

		g.setColor(new Color(0x88D25F));
		g.fillRect(225, 150, 350, 300);
		
		g.setColor(new Color(0x000000));
		g.drawRect(225, 150, 350, 300);
		
		g.drawString("Welcome "+ game.nick, 235, 165);
		
		g.drawString("Room list:", 235, 200);
		
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(2));
		
		g.drawRect(250, 210, 300, 210);
		
		g.drawString("Press 'C' to create room. Use F5 to refresh.", 235, 440);

		Iterator roomIter = game.network.roomNames.iterator();
		
		
		int i = 0;
		while(roomIter.hasNext()) {
			if(i+1 == roomSelectionPos) {
				Color transRectColor = new Color(255, 255,255, 98 );
				g.setColor(transRectColor);
				g.fillRect(255, 218+i*20, 120, 15);
			}
			g.setColor(Color.black);
			String roomName = (String) roomIter.next();
			g.drawString(roomName, 260, 230+i*20);
			i++;
		}
		
		
		g.setStroke(oldStroke);

	}
	public void connectScreen(Graphics2D g) {


		g.setColor(new Color(0x88D25F));
		g.fillRect(225, 250, 350, 120);

		g.setColor(new Color(0x000000));
		g.drawRect(225, 250, 350, 120);

		g.drawString("Server ip:", 260, 280);
		g.drawString("Nick:", 260, 305);

		g.drawString(game.serverIP, 335, 280);
		g.drawString(game.nick, 335, 305);

		g.drawRect(330, 265, 100, 20);
		g.drawRect(330, 290, 100, 20);

		String connectionString = "";

		if (game.connectionStatus == 0) {
			connectionString = "Disconnected.";
		} else if (game.connectionStatus == 1) {
			connectionString = "Connecting...";
		} else if (game.connectionStatus == 2) {
			connectionString = "Connected!";
		} else if (game.connectionStatus == 100) {
			connectionString = "Fail!";
		}

		g.drawString(connectionString, 240, 340);

		g.drawString("Use Shift to transition. Press enter to connect.", 240,
				365);
	}

}
