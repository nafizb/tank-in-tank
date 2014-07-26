package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import network.MainConnection;

import screen.Game;
import screen.Menu;
import screen.screenInterface;

import entities.Entity;
import entities.tank;


@SuppressWarnings("serial")
public class game extends JPanel implements Runnable,ActionListener {
	
	//public static Entity[] entities = new Entity[30];
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static int showFps = 1;
	public static boolean isConnected = false;
	
	public static String serverIP = new String("127.0.0.1");
	public static String nick = new String("nafiz");

	public static boolean[] keys = new boolean[300];
	
	screenInterface gameScreen = new Game();
	static public screenInterface menuScreen = new Menu();
	
	static public int port = 7283;

	static public int connectionStatus = 0;

	
	public static MainConnection network = new MainConnection();
	game() {
		
		//entities[0] = new tank(100,100);
		entities.add(new tank(200,100));
		network.start();
	}
	public void paint(Graphics g) 	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
	      RenderingHints rh =
	            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                               RenderingHints.VALUE_ANTIALIAS_ON);

	      rh.put(RenderingHints.KEY_RENDERING,
	             RenderingHints.VALUE_RENDER_QUALITY);

	      g2.setRenderingHints(rh);

		if(isConnected)
			gameScreen.draw(g2);
		else 
			menuScreen.draw(g2);
    }
	
	public void run() {
		int fps = 0;

		long time = System.currentTimeMillis();
		while(true) {
			keyUpdate();
			entityUpdate();
			if(System.currentTimeMillis() - time > 1000 ) {
				time = System.currentTimeMillis();
				
				showFps = fps;
				fps = 0;
			
			}
			fps++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			repaint();
		}
		
	}
	public void entityUpdate() {
		for(Object e : entities.toArray()) {
			if(e == null) continue;
			
			Entity en = (Entity)e;
			en.update();
		}
	}
	public void keyUpdate() {
		
		 if(keys[68]) {
	    	if(entities.get(0).angleVec < 1.5) entities.get(0).angleVec += 0.15;
	     }

	     if(keys[65]) {
		    if(entities.get(0).angleVec > -1.5) entities.get(0).angleVec -= 0.15;
	     }
	     if(keys[87]) {
	    	 if(entities.get(0).yVec < entities.get(0).maxVec) entities.get(0).yVec += 0.2f;
	    	 if(entities.get(0).xVec < entities.get(0).maxVec) entities.get(0).xVec += 0.2f;
	     }
	     if(keys[83]) {
	    	 if(entities.get(0).yVec > -entities.get(0).maxVec) entities.get(0).yVec -= 0.2f;
	    	 if(entities.get(0).xVec > -entities.get(0).maxVec) entities.get(0).xVec -= 0.2f;
	     }
	    
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

