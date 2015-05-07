package entitystuff;

import game.Map;

import java.io.IOException;

import javax.imageio.ImageIO;

public class GameCharacter extends Entity {

	int dir=0, step=0;
	int elapsed=0;
	int stepTime=10;
	
	SpriteSet sprites;
	
	public GameCharacter(Map m, String path, int x, int y) {
		super(m, null,x,y);
		
		try {
			sprites = new SpriteSet(ImageIO.read(this.getClass().getResource(path)));
			super.image = sprites.getSprite(0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(int time) {
		super.update(time);
		
		elapsed+=time;
		if(elapsed > stepTime) {
			elapsed-=stepTime;
			step++;
			step %= 8;
		}
		
		if(Math.abs(super.xMag) > Math.abs(super.yMag)) 
			if(super.xMag > 0)
				dir = 0;
			else
				dir = 2;
		else
			if(super.yMag > 0)
				dir = 1;
			else
				dir = 3;
		
		super.image = sprites.getWalkingSprite(dir, step);
	}
}
