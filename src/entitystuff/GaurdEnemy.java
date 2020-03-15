package entitystuff;

import game.GameMap;

import java.awt.Point;

public class GaurdEnemy extends Character {
	public static final int CLOSE_ENOUGH  = 20;
	public static final int SIGHTRANGE  = 150;
	public static final int DAMAGE = 1;
	
	Entity player;
	Point gaurdPos, target;
	
	public GaurdEnemy(GameMap m, int x, int y, Entity player) {
		super(m, "enemy.png", x, y);
		this.player = player;
		this.gaurdPos = new Point(x,y);
		
		speed = 0.7;
		type = Entity.TYPE_ENEMY;
	}
	
	public void update(int time) {
		super.update(time);
		target = player.getPos();
		
		double p1Dist = distTo(gaurdPos);
		double targetDist = distTo(target);
		
		if(targetDist < SIGHTRANGE) {
			moveTowards(target,targetDist);
		} else {
			moveTowards(gaurdPos,p1Dist);
		}
		
		Entity entity;
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			
			if(distTo(entity.getPos()) < CLOSE_ENOUGH && entity.type == Entity.TYPE_PLAYER) {
				entity.damage(DAMAGE);
				return;
			}
		}
	}
}
