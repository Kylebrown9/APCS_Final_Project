package spells;

import game.LightImage;
import game.Player;

public class MagicBoltSpell extends Spell {
	LightImage image;
	
	public MagicBoltSpell(Player p, int level) {
		super(p.pC.m, p, level, 200, 1);
		image = new LightImage("MagicBall.png");
	}

	public void doSpellAction(int x, int y) {
		m.entities.add(new MagicBolt(m,image,p.getPos().x,p.getPos().y,p.getPos().x+x,p.getPos().y+y));
	}
}
