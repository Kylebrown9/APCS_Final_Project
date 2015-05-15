package spells;

import entitystuff.Entity;
import game.LightImage;

public class LightningEffect extends PassiveEffect {

	int duration = 10;
	int count=0;
	
	public LightningEffect(Entity effected) {
		super("RecentStrike",effected,10);
	}
	
	public void update() {
		count++;
	}
	
	public void drawOn(LightImage i) {
		
	}
	
	public boolean isDone() {
		return count > duration;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	
}
