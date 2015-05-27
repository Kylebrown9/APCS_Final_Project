package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import entitystuff.ChasingEnemy;
import entitystuff.Entity;
import entitystuff.PlayerCharacter;

public class GameMap {
	public static final int RES = 25;
	public static final int BACKGR = 14211288;	//background
	public static final int PSPAWN = 16711680;	//player spawn
	public static final int ESPAWN = 3800839;	//enemy spawn points
	public static final int VICTORY = 255;	//victory area
	
	
	protected int[][] map;
	
	private LightImage[] tiles;
	
	public int xOff=0, yOff=0, pXOff=0, pYOff=0;
	
	public List<Entity> entities = new ArrayList<Entity>();
	public PlayerCharacter p;
	
	public List<Point> backgroundTiles = new ArrayList<Point>();
	List<Point> playerSpawnTiles = new ArrayList<Point>();
	List<Point> enemySpawnTiles = new ArrayList<Point>();
	List<Point> victoryAreaTiles = new ArrayList<Point>();
	List<Point> goodTiles = new ArrayList<Point>();
	LightImage bitMap;
	
	int xP=0;
	
	public GameMap(LightImage source,int pType) {
		this.bitMap = source;
		
		map = new int[bitMap.width][bitMap.height];
		
		backgroundTiles = bitMap.getPointsWithColor(BACKGR);
		playerSpawnTiles = bitMap.getPointsWithColor(PSPAWN);
		enemySpawnTiles = bitMap.getPointsWithColor(ESPAWN);
		victoryAreaTiles = bitMap.getPointsWithColor(VICTORY);
		
		goodTiles.addAll(backgroundTiles);
		goodTiles.addAll(playerSpawnTiles);
		goodTiles.addAll(enemySpawnTiles);
		goodTiles.addAll(victoryAreaTiles);
		
		Point point;
		for(int i=0; i<goodTiles.size(); i++) {
			point = goodTiles.get(i);
			map[point.x][point.y] = 1;
		}
		for(int i=0; i<victoryAreaTiles.size(); i++) {
			point = victoryAreaTiles.get(i);
			map[point.x][point.y] = 3;
		}
		
		if(playerSpawnTiles.size() != 1)
			System.err.println("Incorrect number of player spawns designated");
		
		p = new PlayerCharacter(this,pType,playerSpawnTiles.get(0).x*RES,playerSpawnTiles.get(0).y*RES);
		entities.add(p);
		
		for(int i=0; i<enemySpawnTiles.size(); i++) {
			point = enemySpawnTiles.get(i);
			entities.add(new ChasingEnemy(this,point.x*RES,point.y*RES,p));
		}
		//***************************************************************
		String[] pathsJPG = {
				"stone1B.jpg",
				"stone2B.png",
				"wood1B.png",
				"carpet0B.jpg",
				"grass0B.png",
				"step0B.jpg"
		};
		
		tiles = new LightImage[pathsJPG.length];
		
		for(int i=0; i<pathsJPG.length; i++) {
			tiles[i] = new LightImage(pathsJPG[i]);
		}
	}
	
	public boolean inBounds(int x, int y, int width, int height) {
		Point point1 = new Point((x-width/2)/RES,(y-height/2)/RES);
		Point point2 = new Point((x-width/2)/RES,(y+height/2)/RES);
		Point point3 = new Point((x+width/2)/RES,(y-height/2)/RES);
		Point point4 = new Point((x+width/2)/RES,(y+height/2)/RES);
		
		return goodTiles.contains(point1) && goodTiles.contains(point2) && 
				goodTiles.contains(point3) && goodTiles.contains(point4);
	}
	
	public void addXP(int amount) {
		xP += amount;
	}
	
	public int getXP() {
		int temp = xP;
		xP = 0;
		return temp;
	}
	
	public boolean hasWon(Point p) {
		return victoryAreaTiles.contains(p);
	}
	
	public void drawOn(LightImage i) {
		for(int x=0; x<bitMap.width; x++)
			for(int y=0; y<bitMap.height; y++)
				tiles[map[x][y]].drawOn(i, x*RES+i.width/2-xOff, y*RES+i.height/2-yOff);
	}
}