package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import main.game;

public class tank extends Entity{
	
	float barrelW, barrelH;
	public tank(float x, float y) {
		this.x = x;
		this.y = y;
		w = 80;
		h = 50;
		angle = 0;
		friction = 0.07f;
		angleFric = 0.1;
		maxVec = 3;
		barrelW = 46;
		barrelH = 3;
		barrelAngle = 0;
	}
	
	@Override
	public void update() {
		frictionUpdate();
		movementUpdate();

	}
	
	public void frictionUpdate() {
		if(xVec > 0 ) {
			xVec -= friction;
			if(xVec < 0)
				xVec = 0;
		}else if(xVec < 0) {
			xVec += friction;
			if(xVec > 0) 
				xVec = 0;
		}
		
		if(yVec > 0 ) {
			yVec -= friction;
			if(yVec < 0)
				yVec = 0;
		}else if(yVec < 0) {
			yVec += friction;
			if(yVec > 0) 
				yVec = 0;
		}
		
		if(angleVec > 0 ) {
			angleVec -= angleFric;
			if(angleVec < 0)
				angleVec = 0;
		}else if(angleVec < 0) {
			angleVec += angleFric;
			if(angleVec > 0) 
				angleVec = 0;
		}
	}
	
	public void movementUpdate() {
		angle += angleVec;
		x += xVec * Math.cos(Math.toRadians(angle));
		y += yVec * Math.sin(Math.toRadians(angle));
	}
	
	public void fire() {
		game.entities.add(new bullet(x+Math.cos(barrelAngle)*barrelW ,y+Math.sin(barrelAngle)*barrelW, barrelAngle));
	}
	public void draw(Graphics2D g2) {
		//draw body
		Rectangle2D body = new Rectangle2D.Float(x-w/2, y-h/2, w ,h);
		AffineTransform at =
            AffineTransform.getTranslateInstance(w / 2, h / 2);
		at.setToRotation(Math.toRadians(angle), x, y);
        
		g2.setColor(new Color(0x49544A));

        g2.fill(at.createTransformedShape(body));
        
        //draw barrel
		Rectangle2D barrel = new Rectangle2D.Float(x, y-1, barrelW ,barrelH);
				
		AffineTransform at2 =
            AffineTransform.getTranslateInstance(0, 0);
		at2.setToRotation((barrelAngle), x, y);
		g2.setColor(new Color(0x000000));

        g2.fill(at2.createTransformedShape(barrel));
        
	}

	
} 
