package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import entitystuff.Entity;
import entitystuff.Player;

public class Game implements Drawable {
	public static final int interval = 10;
	
	int xOff=0, yOff=0;
	Map map;
	Player p;
	List<Entity> entities;
	Image image;
	Graphics g;
	
	public Game(Map map) {
		map = new Map(500,500);
		p = new Player(map,0,0);
		
		this.map = map;
		entities = new ArrayList<Entity>();
		
		for(int i=0; i<entities.size(); i++)
			entities.add(map.npcs.get(i));
		
		entities.add(p);
	}
	
	public void input(int x, int y) {
		map.xOff = p.getX()-map.getWidth()/2;
		map.yOff = p.getY()-map.getHeight()/2;
		
		p.setTrajectory(Math.atan((x-250)/(y-250)));
	}
	
	@Override
	public Image toImage() {
//		image = ImageStuff.copyImage(map.toImage());
		image = map.toImage();
		g = image.getGraphics();
		
		for(int i=0; i<entities.size(); i++)
			entities.get(i).drawOn(g);
		
		return image;
	}
	
	public void drawOn(Graphics g) {
		g.drawImage(map.toImage(), 0, 0, null);
		
		for(int i=0; i<entities.size(); i++)
			entities.get(i).drawOn(g);
	}
	
	public void update() {
		for(int i=0; i<entities.size(); i++)
			entities.get(i).update(interval);
	}

}
