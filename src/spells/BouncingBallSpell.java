package spells;

import game.LightImage;
import game.Player;

public class BouncingBallSpell extends Spell {

	LightImage image;
	
	public BouncingBallSpell(Player p, int level) {
		super(p.pC.m, p, level, 1000, 10);
		image = new LightImage("BouncyFire.png");
	}

	public void doSpellAction(int x, int y) {
		m.entities.add(new BouncingBall(m,image,p.getPos().x,p.getPos().y,p.getPos().x+x,p.getPos().y+y));
	}
}
