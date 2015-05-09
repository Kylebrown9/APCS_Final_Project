package maps;

import entitystuff.Dummy;
import entitystuff.Entity;
import entitystuff.PlayerCharacter;
import game.Map;

import java.awt.Rectangle;

public class Dungeon extends Map {

	public Dungeon() {
		super(50, 50);
		
		setRect(1,0,0,20,20);
		
		addRect(2,new Rectangle(2,2,5,5));
		addRect(2,new Rectangle(4,5,1,5));
		addRect(2,new Rectangle(2,8,5,5));
		addRect(2,new Rectangle(5,9,5,3));
		addRect(2,new Rectangle(8,8,5,5));
		
//		boundaries.add(new Rectangle(0,0,50*Map.RES,50*Map.RES));
		
//		super.npcs.add(new Enemy((Map)this,500,500,(Entity)p,new Point(500,500),new Point(500,520)));
		Entity thingy = new Dummy(this,120,120);
		entities.add(thingy);
		p = new PlayerCharacter(this,120,120);
	}

}
