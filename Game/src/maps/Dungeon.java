package maps;

import entitystuff.Player;
import game.Map;

import java.awt.Rectangle;

public class Dungeon extends Map {

	public Dungeon(Player p) {
		super(50, 50);
		
		setRect(1,0,0,20,20);
		
		addRect(2,new Rectangle(2,2,5,5));
		addRect(2,new Rectangle(4,5,1,5));
		addRect(2,new Rectangle(2,8,5,5));
		addRect(2,new Rectangle(5,9,5,3));
		addRect(2,new Rectangle(8,8,5,5));
		
//		super.npcs.add(new Enemy((Map)this,500,500,(Entity)p,new Point(500,500),new Point(500,520)));
	}

}
