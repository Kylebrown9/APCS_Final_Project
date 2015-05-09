package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import maps.Dungeon;
import updaters.Updatable;
import entitystuff.Entity;
import entitystuff.Player;

public class Game implements Updatable {
	public static final int interval = 1;
	
	Map map;
	public Player p;
	List<Entity> entities;
	BufferedImage image;
	Graphics g;
	
	public Game() {
		map = new Dungeon(p);
		p = new Player(map,120,120);
		
		entities = new ArrayList<Entity>();
		
		for(int i=0; i<map.npcs.size(); i++)
			entities.add(map.npcs.get(i));
		
		entities.add(p);
//		p.setMag(1, 0);
	}
	
	public void input(int x, int y) {
		p.setTargetRelative(x-250,y-250);
		
		update();
	}
	
	public void drawOn(LightImage image) {
		map.drawOn(image);

		for(int i=0; i<entities.size(); i++)
			entities.get(i).drawOn(image);
	}
	
	public void update() {
		map.pXOff = p.image.width/2;
		map.pYOff = p.image.height/2;
		map.xOff = p.getX();
		map.yOff = p.getY();
		
		for(int i=0; i<entities.size(); i++)
			entities.get(i).update(interval);
	}

}
