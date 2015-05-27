package entitystuff;

import game.GameMap;

import java.awt.Point;

public class ChasingEnemy extends Character {
	public static final int CLOSE_ENOUGH  = 20;
	public static final int SIGHTRANGE  = 200;
	public static final int DAMAGE = 1;
	public static final int XPVALUE = 100;
	
	Entity player;
	Point gaurdPos, target;
	int counter=0, interval=10;
	
	public ChasingEnemy(GameMap m, int x, int y, Entity player) {
		super(m, "enemy.png", x, y);
		this.player = player;
		this.gaurdPos = new Point(x,y);
		
		baseSpeed = 1.2;
		type = Entity.TYPE_ENEMY;
	}
	
	public void update(int time) {
		super.update(time);
		target = player.getPos();
		double targetDist = distTo(target);
		
		if(targetDist < SIGHTRANGE) {
			moveTowards(target,targetDist);
		} else {
			moveTowards(getPos(),0);
		}
		
		Entity entity;
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			
			if(distTo(entity.getPos()) < CLOSE_ENOUGH && entity.type == Entity.TYPE_PLAYER) {
				counter++;
				counter %= interval;
				
				if(counter == 0)
					entity.damage(DAMAGE);
				return;
			}
		}
	}
	
	public boolean isDead() {
		boolean a = super.isDead();
		if(a)
			m.addXP(XPVALUE);
		return a;
	}
}
