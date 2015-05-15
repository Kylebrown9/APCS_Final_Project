package spells;

import java.io.IOException;

import javax.imageio.ImageIO;

import entitystuff.PlayerCharacter;
import game.LightImage;

public class FireBallSpell extends Spell {

	LightImage image;
	
	public FireBallSpell(PlayerCharacter pC, int level) {
		super(pC.m, pC, level, 1000);
		
		try {
			image = new LightImage(ImageIO.read(this.getClass().getResource("/Resources/" + "fireball.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSpellAction(int x, int y) {
		m.entities.add(new FireBall(m,image,p.getPos().x,p.getPos().y,p.getPos().x+x,p.getPos().y+y));
	}
}
