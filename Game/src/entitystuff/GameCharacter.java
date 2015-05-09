package entitystuff;

import game.Map;

public class GameCharacter extends Entity {

	int dir=0, step=0;
	int elapsed=0;
	int stepTime=5;
	
	SpriteSet sprites;
	
	public GameCharacter(Map m, String path, int x, int y) {
		super(m,x,y);
		
		sprites = new SpriteSet(path);
		super.image = sprites.getSprite(0, 0);
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
				dir = 3;
			else
				dir = 1;
		else
			if(super.yMag > 0)
				dir = 2;
			else
				dir = 0;
		
		if(super.xMag == 0 && super.yMag == 0)
			dir = -1;
		
		super.image = sprites.getSprite(dir+8, step);
	}
}
