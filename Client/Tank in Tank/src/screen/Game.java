package screen;

import java.awt.Color;
import java.awt.Graphics2D;

import main.game;

import entities.Entity;

public class Game extends screenInterface{

	@Override
	public void draw(Graphics2D g) {


	      g.setColor(new Color(0x007808));
	      g.fillRect(0, 0, 800, 600);
	      
	      for(Object e : game.entities.toArray()) {
	    	  if(e == null ) continue;
	    	  Entity en = (Entity) e;
	    	  en.draw(g);
	      }
	      
	      g.setColor(new Color(0xFFFFFF));
	      g.drawOval((int)(game.entities.get(0).x-1), (int)(game.entities.get(0).y-1), 2, 2);
	      g.drawString("FPS: "+ game.showFps, 740, 565);
	}

}
