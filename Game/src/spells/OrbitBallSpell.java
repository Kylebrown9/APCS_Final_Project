package spells;

import game.LightImage;
import game.Player;

public class OrbitBallSpell extends Spell {
	LightImage image;
	
	public OrbitBallSpell(Player p, int level) {
		super(p.pC.m, p, level, 100, 3);
		image = new LightImage("MagicBall.png");
	}

	public void doSpellAction(int x, int y) {
		m.entities.add(new OrbitBall(m,image,p.getPos().x,p.getPos().y,p.getPos().x+x,p.getPos().y+y));
	}
}
