package spells;

import entitystuff.Entity;
import game.GameMap;
import game.ImageArray;
import game.LightImage;

public class OrbitBall extends Entity {

	public static final double INTERVAL = Math.PI/300;
	
	String[] paths = {"OribtFire1.png","OribtFire2.png","OribtFire3.png","OribtFire4.png"};
	ImageArray images = new ImageArray(paths);
	int count=0;
	
	public OrbitBall(GameMap m, LightImage fball, int x, int y, int targetX, int targetY) {
		super(m, x, y);
		image = images.getImage(count);
		type = Entity.TYPE_NOCOLLISION;
		showHealth = false;
	}

	public void update(int time) {
		Entity entity;
		x = m.p.getX() + 50*Math.cos((double)count*INTERVAL);
		y = m.p.getY() + 50*Math.sin((double)count*INTERVAL);
		pos.x = (int)x;
		pos.y = (int)y;
		
		count++;
		
		image = images.getImage(count);
		
		if(count%10!=0)
			return;
		
		for(int i=0; i<m.entities.size(); i++) {
			entity = m.entities.get(i);
			if(distTo(entity.getPos()) < 10 && entity.type == Entity.TYPE_ENEMY)
				entity.damage(1);
		}
	}
}
