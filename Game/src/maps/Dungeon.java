package maps;

import entitystuff.Entity;
import entitystuff.GaurdEnemy;
import entitystuff.PlayerCharacter;
import game.GameMap;

import java.awt.Rectangle;

public class Dungeon extends GameMap {

	public Dungeon() {
		super(50, 50);
		
		int xOffset=20, yOffset=20;
		
		setRect(1,0,0,50,50);
		
		addRect(2,new Rectangle(2+xOffset,2+yOffset,5,5));
		addRect(2,new Rectangle(4+xOffset,5+yOffset,1,5));
		addRect(2,new Rectangle(2+xOffset,8+yOffset,5,5));
		addRect(2,new Rectangle(5+xOffset,9+yOffset,5,3));
		addRect(2,new Rectangle(8+xOffset,8+yOffset,5,20));
		
		p = new PlayerCharacter(this,120+xOffset*RES,120+yOffset*RES);
		
		Entity enemy1 = new GaurdEnemy((GameMap)this,250+xOffset*RES,300+yOffset*RES,(Entity)p);
		Entity enemy2 = new GaurdEnemy((GameMap)this,270+xOffset*RES,300+yOffset*RES,(Entity)p);
		Entity enemy3 = new GaurdEnemy((GameMap)this,260+xOffset*RES,320+yOffset*RES,(Entity)p);
		entities.add(enemy1);
		entities.add(enemy2);
		entities.add(enemy3);
	}

}
