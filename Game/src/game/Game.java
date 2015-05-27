package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import runner.GameOverLayer;
import runner.GameWonLayer;
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
	
	GameOverLayer gameOver;
	GameWonLayer gameWon;
	
	public Game(Player player, int pType, GameOverLayer gO, GameWonLayer gW) {
		map = new GameMap(new LightImage("dungeon1.png"),pType);
		this.player = player;
		player.setPC(map.p);
		entities = map.entities;
		gameOver = gO;
		gameWon = gW;
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
		case KeyEvent.VK_T: val=4;
			break;
		case KeyEvent.VK_Y: val=5;
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
		map.pXOff = map.p.image.width/2;
		map.pYOff = map.p.image.height/2;
		map.xOff = map.p.getX();
		map.yOff = map.p.getY();
		
		player.giveXP(map.getXP());
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
		
		if(player.pC.isDead())
			gameOver.active = true;
		Point p = player.pC.getPos();
		Point p1 = new Point(p.x/25,p.y/25);
		if(map.hasWon(p1)) {
			gameWon.active = true;
			player.pC.damageable = false;
		}
			
	}
	
	public void setDim(Dimension dim) {
		windowDim = dim;
	}
}
