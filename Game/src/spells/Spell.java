package spells;

import entitystuff.PlayerCharacter;
import game.GameMap;
import game.Player;

public class Spell {
	GameMap m;
	Player player;
	PlayerCharacter p;
	long coolDown =0;
	long lastCast;
	
	int manaCost;
	
	public Spell(GameMap m, Player p, int level, long coolDown, int manaCost) {
		this.m = m;
		this.p = p.pC;
		player =  p;
		this.manaCost = manaCost;
		this.coolDown = coolDown;
		lastCast = System.currentTimeMillis()-coolDown;
	}
	
	public void cast(int x, int  y) {
		long now = System.currentTimeMillis();
		if(now - lastCast > coolDown && player.mana > manaCost) {
			doSpellAction(x,y);
			lastCast = now;
			player.mana -= manaCost;
		}
	}
	
	public void doSpellAction(int x, int y) {}
}
