package entitystuff;

import game.LightImage;
import game.Map;

public class FireBall extends Entity {

	public static final int DAMAGE = 20;
	public static final int CLOSE_ENOUGH  = 1;
	
	int targetX, targetY;
	
	public FireBall(Map m, LightImage fball, int x, int y, int targetX, int targetY) {
		super(m, x, y);
		
		double xDiff = targetX-x;
		double yDiff = targetY-y;
		
		double dist =  Math.sqrt(xDiff*xDiff + yDiff*yDiff);

		this.setMag(xDiff/dist, yDiff/dist);
		
		image = fball;
		
		type = 2;
	}

	public void update(int time) {
		super.update(time);
		
//		for(int i=0; i<m.entities.size(); i++) {
//			if(distTo(m.entities.get(i).getPos()) < CLOSE_ENOUGH && 
//					m.entities.get(i).type != 1 && 
//					m.entities.get(i).type != 2) {
//				m.entities.get(i).damage(DAMAGE);
//				this.health = 0;
//				return;
//			}
//		}
	}
}
