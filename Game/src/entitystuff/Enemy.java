package entitystuff;

import game.Map;

import java.awt.Point;

public class Enemy extends GameCharacter {
	public static final int CLOSE_ENOUGH  = 1;
	public static final int SIGHTRANGE  = 50;
	
	public static final int PLAYER  = 0;
	public static final int PATROL1  = 1;
	public static final int PATROL2  = 2;
	
	Entity player;
	Point p1, p2, target;
	
	int destID;
	
	public Enemy(Map m, int x, int y, Entity player, Point patrol1, Point patrol2) {
		super(m, "/Resources/skeleton.png", x, y);
		this.player = player;
		p1 = patrol1;
		p2 = patrol2;
	}
	
	public void update(int time) {
		super.update(time);
		target = player.getPos();
		
		double p1Dist = distTo(p1);
		double p2Dist = distTo(p2);
		double targetDist = distTo(target);
		
		if(targetDist < SIGHTRANGE) {
			destID = PLAYER;
			moveTowards(target,targetDist);
		} else {
			if(destID == PATROL1) {
				if(p1Dist < CLOSE_ENOUGH) {
					destID = PATROL2;
					moveTowards(p2,p2Dist);
				} else {
					moveTowards(p1,p1Dist);
				}
			} else if (destID == PATROL2) {
				if(p2Dist < CLOSE_ENOUGH) {
					destID = PATROL1;
					moveTowards(p1,p2Dist);
				} else {
					moveTowards(p2,p1Dist);
				}
			}
		}
	}
	
	public double distTo(Point dest) {
		double xDiff = dest.x-x;
		double yDiff = dest.y-y;
		
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	
	public void moveTowards(Point dest, double dist) {
		if(dist < CLOSE_ENOUGH)
			this.setMag(0, 0);
		else
			this.setMag((dest.x-x)/dist, (dest.y-y)/dist);
	}

}
