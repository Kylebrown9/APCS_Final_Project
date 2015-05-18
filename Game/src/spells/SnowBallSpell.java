package spells;

import game.LightImage;
import game.Player;

import java.io.IOException;

import javax.imageio.ImageIO;

public class SnowBallSpell extends Spell {
LightImage image;
	
	public SnowBallSpell(Player p, int level) {
		super(p.pC.m, p, level, 1000, 10);
		
		try {
			image = new LightImage(ImageIO.read(this.getClass().getResource("/Resources/" + "snowball.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSpellAction(int x, int y) {
		m.entities.add(new SnowBall(m,image,p.getPos().x,p.getPos().y,p.getPos().x+x,p.getPos().y+y));
	}
}
