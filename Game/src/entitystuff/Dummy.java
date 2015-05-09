package entitystuff;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.LightImage;
import game.Map;

public class Dummy extends Entity {

	public Dummy(Map m, int x, int y) {
		super(m, x, y);
		
		try {
			super.image = new LightImage(ImageIO.read(this.getClass().getResource("/Resources/" + "fireball.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		type = Entity.TYPE_ENEMY;
	}
	
	public boolean isDead() {
		return super.isDead();
	}
}
