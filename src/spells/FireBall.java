package spells;

import entitystuff.Entity;
import game.LightImage;
import game.GameMap;

public class FireBall extends Entity {

	public static final int DAMAGE = 5;

	public static final int TRIGGER_RANGE = 10;
	public static final int BLAST_RADIUS  = 20;
	
	int targetX, targetY;
	
	public FireBall(GameMap m, LightImage fball, int x, int y, int targetX, int targetY) {
		super(m, x, y);
		
		double xDiff = targetX-x;
		double yDiff = targetY-y;
		
		double dist =  Math.sqrt(xDiff*xDiff + yDiff*yDiff);

		this.setMag(xDiff/dist, yDiff/dist);
		
		image = fball;
		
		baseSpeed = 3;
		
		type = Entity.TYPE_NOCOLLISION;
		
		showHealth = false;
	}

	public void update(int time) {
		super.update(time);
		Entity entity;
		
		if(xCollision() || yCollision()) {
			this.health = 0;
			return;
		}
		
		boolean firing=false;
		
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			if(distTo(entity.getPos()) < TRIGGER_RANGE && entity.type == Entity.TYPE_ENEMY)
				firing = true;
		}
		
		if(!firing)
			return;
		
		this.health = 0;
		
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			
			if(distTo(entity.getPos()) < BLAST_RADIUS && entity.type == Entity.TYPE_ENEMY)
				entity.damage(DAMAGE);
		}
	}
}
