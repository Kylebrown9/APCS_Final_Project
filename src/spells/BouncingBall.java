package spells;

import entitystuff.Entity;
import game.GameMap;
import game.LightImage;

public class BouncingBall extends Entity {

	public static final int DAMAGE = 5;

	public static final int TRIGGER_RANGE = 10;
	public static final int BLAST_RADIUS  = 20;
	
	int targetX, targetY;
	int count=0;
	
	public BouncingBall(GameMap m, LightImage fball, int x, int y, int targetX, int targetY) {
		super(m, x, y);
		
		double xDiff = targetX-x;
		double yDiff = targetY-y;
		
		double dist =  Math.sqrt(xDiff*xDiff + yDiff*yDiff);

		this.setMag(xDiff/dist, yDiff/dist);
		
		image = fball;
		
		baseSpeed = 5;
		
		type = Entity.TYPE_NOCOLLISION;
		
		showHealth = false;
	}

	public void update(int time) {
		super.update(time);
		Entity entity;
		
		if(xCollision() && yCollision()) {
			xMag *= -1;
			yMag *= -1;
			count++;
		} else if (xCollision()) {
			xMag *= -1;
			count++;
		} else if (yCollision()) {
			yMag *= -1;
			count++;
		}
		
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			
			if(distTo(entity.getPos()) < BLAST_RADIUS && entity.type == Entity.TYPE_ENEMY)
				entity.damage(DAMAGE);
		}
		
		if(count == 8)
			this.health = 0;
	}
}
