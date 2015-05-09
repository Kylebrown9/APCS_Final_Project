package entitystuff;

import game.LightImage;
import game.Map;

public class PlayerCharacter extends GameCharacter {

	public static final int CLOSE_ENOUGH  = 1;
	
	int targetX, targetY;
	
	
	public PlayerCharacter(Map m, int x, int y) {
//		super(m, "/Resources/playerPNG.png",x,y);
		super(m, "/Resources/player.png",x,y);
		targetX = x;
		targetY = y;
		
		width = 20;
		height = 50;
		
		type = 1;
	}
	
	public void setTarget(int x, int y) {
		targetX = x;
		targetY = y;
	}
	
	public void setTargetRelative(int x, int y) {
		targetX = pos.x + x;
		targetY = pos.y + y;
	}
	
	public void update(int time) {
		super.update(time);
		
		double xDiff = targetX-x;
		double yDiff = targetY-y;
		
		double dist =  Math.sqrt(xDiff*xDiff + yDiff*yDiff);
		
		if(dist < CLOSE_ENOUGH)
			this.setMag(0, 0);
		else {
			this.setMag(xDiff/dist, yDiff/dist);
		}
	}
	
	public void drawOn(LightImage i) {
		image.drawOn(i, i.width/2-image.width/2, i.height/2-image.height/2);
	}
}
