package entitystuff;

import game.LightImage;
import game.Map;

public class FireBall extends Entity {

	public static final int DAMAGE = 20;
	public static final int CLOSE_ENOUGH  = 10;
	
	int targetX, targetY;
	
	public FireBall(Map m, LightImage fball, int x, int y, int targetX, int targetY) {
		super(m, x, y);
		
		double xDiff = targetX-x;
		double yDiff = targetY-y;
		
		double dist =  Math.sqrt(xDiff*xDiff + yDiff*yDiff);

		this.setMag(xDiff/dist, yDiff/dist);
		
		image = fball;
		
		type = Entity.TYPE_NOCOLLISION;
		wallEffect = true;
	}

	public void update(int time) {
		super.update(time);
		Entity entity;
		
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			
			if(distTo(entity.getPos()) < CLOSE_ENOUGH && entity.type == Entity.TYPE_ENEMY) {
				entity.damage(DAMAGE);
				this.health = 0;
				return;
			}
		}
	}
}
