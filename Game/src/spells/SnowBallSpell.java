package spells;

import game.LightImage;
import game.Player;

public class SnowBallSpell extends Spell {
LightImage image;
	
	public SnowBallSpell(Player p, int level) {
		super(p.pC.m, p, level, 1000, 10);
		image = new LightImage("snowball.png");

	}

	public void doSpellAction(int x, int y) {
		m.entities.add(new SnowBall(m,image,p.getPos().x,p.getPos().y,p.getPos().x+x,p.getPos().y+y));
	}
}
