package spells;

import entitystuff.Entity;
import game.LightImage;
import game.GameMap;

public class LightningBolt extends Entity {

	int generation;
	int duration = 10;
	int damage = 100;
	
	Entity source, target;
	
	public LightningBolt(GameMap m, Entity source, Entity target) {
		super(m,0,0);
		this.generation = 0;
		this.source = source;
		this.target = target;
	}
	
	public LightningBolt(GameMap m, Entity source, Entity target, int generation) {
		super(m,0,0);
		this.generation = generation;
	}
	
	public void update() {
		
	}

	public void drawOn(LightImage i) {
		i.drawLineOn(source.getPos(), target.getPos());
	}
}
