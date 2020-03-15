package spells;

import entitystuff.Entity;
import game.GameMap;
import game.LightImage;

public class MagicBolt extends Entity {
	public MagicBolt(GameMap m, LightImage image1, int x, int y, int targetX, int targetY) {
		super(m, x, y);
		
		double xDiff = targetX-x;
		double yDiff = targetY-y;
		
		double dist =  Math.sqrt(xDiff*xDiff + yDiff*yDiff);

		this.setMag(xDiff/dist, yDiff/dist);
		
		image = image1;
		
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
		
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			if(distTo(entity.getPos()) < 30 && entity.type == Entity.TYPE_ENEMY) {
				entity.damage(1);
				this.health = 0;
			}
		}
	}
}
