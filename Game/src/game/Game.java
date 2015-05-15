package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import maps.Dungeon;
import updaters.Updatable;
import entitystuff.Entity;
import entitystuff.PlayerCharacter;

public class Game implements Updatable {
	public static final int interval = 1;
	
	GameMap map;
	public PlayerCharacter p;
	Player player;
	List<Entity> entities;
	BufferedImage image;
	Graphics g;
	
	Dimension windowDim;
	
	boolean casting;
	int spellID;
	
	public Game() {
		map = new Dungeon();
		player = new Player(map.p);
		entities = map.entities;
	}
	
	public void setMap(GameMap m) {
		map = m;
		player.setPC(map.p);
		entities = map.entities;
	}
	
	public void input(int x, int y) {
		player.input(x-windowDim.width/2,y-windowDim.height/2);
	}
	
	public void input(KeyEvent ke) {
		int val = 10;
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_Q: val=0;
			break;
		case KeyEvent.VK_W: val=1;
			break;
		case KeyEvent.VK_E: val=2;
			break;
		case KeyEvent.VK_R: val=3;
			break;
		default:
		}
		player.startCast(val);
		
		casting = true;
	}
	
	public void drawOn(LightImage image) {
		map.drawOn(image);

		for(int i=0; i<entities.size(); i++)
			entities.get(i).drawOn(image);
	}
	
	public void update() {
		//************************Set Offsets*************************************
		map.pXOff = p.image.width/2;
		map.pYOff = p.image.height/2;
		map.xOff = p.getX();
		map.yOff = p.getY();
		
		//*************************Remove Dead*************************************
		List<Entity> deadEntities = new ArrayList<Entity>();
		
		for (Entity e : entities)
		    if (e.isDead())
		       deadEntities.add(e);

		for (Entity deadEntity : deadEntities)
		    entities.remove(deadEntity);
		 
		//**************************Update Remaining*******************************
		for(int i=0; i<entities.size(); i++)
				entities.get(i).update(interval);
	}
	
	public void setDim(Dimension dim) {
		windowDim = dim;
	}
}
