package entitystuff;

import game.GameMap;

public class Character extends Entity {

	int dir=0, step=0;
	int elapsed=0;
	int stepTime=5;
	
	SpriteSet sprites;
	
	public Character(GameMap m, String path, int x, int y) {
		super(m,x,y);
		
		sprites = new SpriteSet(path);
		super.image = sprites.getSprite(0, 0);
	}

	public void update(int time) {
		super.update(time);
		
		elapsed+=time;
		if(elapsed > stepTime) {
			elapsed-=stepTime;
			step+=3;
			step %= 18;
		}
		
		if(Math.abs(super.xMag) > Math.abs(super.yMag)) 
			if(super.xMag > 0)
				super.image = sprites.getSprite(3+8, step/2);
			else
				super.image = sprites.getSprite(1+8, step/2);
		else
			if(super.yMag > 0)
				super.image = sprites.getSprite(2+8, step/2);
			else
				super.image = sprites.getSprite(0+8, step/2);
		
		if(super.xMag == 0 && super.yMag == 0)
			super.image = sprites.getSprite(14, step/3);
	}
}
