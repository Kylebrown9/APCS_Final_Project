package spells;

import game.GameMap;
import game.Player;

public class NoSpell extends Spell {

	public NoSpell(GameMap m, Player p) {
		super(m,p,0, 10000000, 0);
	}

}
