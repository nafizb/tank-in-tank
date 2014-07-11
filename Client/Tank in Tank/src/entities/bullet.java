package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import main.game;

public class bullet extends Entity{

	bullet(double x, double y, double direction) {
		this.x = (float)x;
		this.y = (float)y;
		w = 2;
		h = 6;
		angle = direction;
	}
	@Override
	public void draw(Graphics2D g2) {
		Rectangle2D barrel = new Rectangle2D.Float(x, y, w ,h);
		
		AffineTransform at2 =
            AffineTransform.getTranslateInstance(0, 0);
		at2.setToRotation((angle+Math.PI/2), x, y);
		g2.setColor(new Color(0x00FFFF0));

        g2.fill(at2.createTransformedShape(barrel));

	}

	@Override
	public void update() {

		 x += 6 * Math.cos(angle);
		 y += 6 * Math.sin(angle);
		 
		 if(checkOutofScreen()) {
			 game.entities.remove(this);
		 }
	}
	public boolean checkOutofScreen() {
		if(x> 800 || x < 0 || y > 600 || y <0 )
			return true;
		else 
			return false;
	}

}
