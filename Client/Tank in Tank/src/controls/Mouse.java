package controls;

import java.awt.event.MouseEvent;

import main.game;

public class Mouse {

	public void mouseMoved(MouseEvent me) {
		 double barrelAngle = Math.atan2( me.getY() - game.entities.get(0).y, me.getX() - game.entities.get(0).x );
		 game.entities.get(0).barrelAngle = barrelAngle;
	}
}
