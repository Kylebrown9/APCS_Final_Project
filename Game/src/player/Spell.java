package player;

import entitystuff.PlayerCharacter;
import game.Map;

public class Spell {
	Map m;
	PlayerCharacter p;
	
	public Spell(Map m, PlayerCharacter pC, int level) {
		this.m = m;
		p = pC;
	}
	
	public void cast(int x, int  y) {}
}
