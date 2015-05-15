package maps;

import game.GameMap;

import java.awt.Rectangle;

public class DefaultMap extends GameMap {
	public DefaultMap() {
		super(50, 50);
		
		//**************************Town Walls******************************
		int wX=1,wY=2,wW=18,wH=20;
		this.setRect(1, wX, wY, wW, wH);
		this.setRect(0, wX+1, wY+1, wW-2, wH-2);
		
		
		//**************************Church**********************************
		int cXOff = 0, cYOff = 0;
		//wall
		this.setRect(1, 5+cXOff, 1+cYOff, 10, 7);
		this.setRect(1, 6+cXOff, 8+cYOff, 8, 5);
				
		//floor
		this.setRect(3, 6+cXOff, 2+cYOff, 8, 5);
		this.setRect(3, 7+cXOff, 7+cYOff, 6, 5);
		
		//carpet
		this.setRect(4, 9+cXOff, 6+cYOff, 2, 7);
		
		//railing
		this.setRect(1, 8+cXOff, 1+cYOff, 4, 5);
		
		//steps
		this.setRect(5, 9+cXOff, 2+cYOff, 2, 4);
		
		int res = GameMap.RES;
		this.boundaries.add(new Rectangle(2*res,4*res,16*res,18*res));
	}

	
}
