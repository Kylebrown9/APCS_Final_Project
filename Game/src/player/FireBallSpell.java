package player;

import entitystuff.FireBall;
import entitystuff.PlayerCharacter;

public class FireBallSpell extends Spell {

	public FireBallSpell(PlayerCharacter pC, int level) {
		super(pC.m, pC, level);
	}

	public void cast(int x, int y) {
		m.entities.add(new FireBall(m,p.getPos().x,p.getPos().y,x,y));
	}
}
