package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import maps.Dungeon;
import player.Player;
import updaters.Updatable;
import entitystuff.Entity;
import entitystuff.PlayerCharacter;

public class Game implements Updatable {
	public static final int interval = 1;
	
	Map map;
	public PlayerCharacter p;
	Player player;
	List<Entity> entities;
	BufferedImage image;
	Graphics g;
	
	Dimension windowDim;
	
	boolean casting;
	
	public Game() {
		map = new Dungeon();
		
		p = map.p;
		
		player = new Player();
		player.setPC(p);
		
		entities = map.entities;
		
		entities.add(p);
//		p.setMag(1, 0);
	}
	
	public void input(int x, int y) {
		if(!casting)
			p.setTargetRelative(x-windowDim.width/2,y-windowDim.height/2);
		else {
			p.setTargetRelative(0,0);
			player.cast(0, x, y);
			casting = false;
		}
			
		
		update();
	}
	
	public void input(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_Q)
			casting = !casting;
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
		
		for(int i=entities.size()-1; i>0; i--)
			if(entities.get(i).health <= 0)
				entities.remove(i);
		
		for(int i=0; i<entities.size(); i++)
				entities.get(i).update(interval);
	}
	
	public void setDim(Dimension dim) {
		windowDim = dim;
	}
}
