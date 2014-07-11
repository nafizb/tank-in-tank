package entities;

import java.awt.Graphics2D;

public abstract class Entity {
	public float x,y, w, h, yVec, xVec, friction, maxVec;
	public double angle, angleVec, angleFric;
	public double barrelAngle;

	public abstract void draw(Graphics2D g2);
	public abstract void update();
	
}
