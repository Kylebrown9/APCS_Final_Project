package player;

import entitystuff.PlayerCharacter;
import game.Map;

public class Spell {
	Map m;
	PlayerCharacter p;
	long coolDown =0;
	long lastCast;
	
	public Spell(Map m, PlayerCharacter pC, int level, long coolDown) {
		this.m = m;
		p = pC;
		this.coolDown = coolDown;
		lastCast = System.currentTimeMillis()-coolDown;
	}
	
	public void cast(int x, int  y) {
		long now = System.currentTimeMillis();
		if(now - lastCast > coolDown) {
			doSpellAction(x,y);
			lastCast = now;
		}
	}
	
	public void doSpellAction(int x, int y) {}
}
