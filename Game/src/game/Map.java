package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import entitystuff.Entity;

public class Map {
	public static final int RES = 25;
	
	protected int[][] map;
	private int width, height;
	
	private BufferedImage[] tiles;
	private LightImage[] lightTiles;
	
	protected List<Rectangle> boundaries;
	public int xOff=0, yOff=0;
	
	public List<Entity> npcs = new ArrayList<Entity>();
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new int[width][height];
		boundaries = new ArrayList<Rectangle>();
		
		//***************************************************************
		String[] pathsJPG = {
				"grass0BJPG.jpg",
				"stone1BJPG.jpg",
				"stone2BJPG.jpg",
				"wood1BJPG.jpg",
				"carpet0BJPG.jpg",
				"step0BJPG.jpg"
		};
		
		tiles = new BufferedImage[pathsJPG.length];
		lightTiles = new LightImage[tiles.length];
		
		for(int i=0; i<pathsJPG.length; i++) {
			try {
				tiles[i] = ImageIO.read(this.getClass().getResource("/Resources/" + pathsJPG[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			lightTiles[i] = new LightImage(tiles[i]);
		}
	}
	
	public void addRect(int type, Rectangle r) {
		setRect(type,r.x,r.y,r.width,r.height);
		boundaries.add(new Rectangle(r.x*RES,r.y*RES,r.width*RES,r.height*RES));
	}
	
	public void setRect(int type, int x, int y, int width, int height) {
		for(int i=x; i<x+width; i++)
			for(int j=y; j<y+height; j++)
				if(i >= 0 && i < this.width && j >= 0 && j < this.height)
					map[i][j] = type;
	}
	
	public boolean inBounds(int x, int y, int width, int height) {
		Point point1 = new Point(x-width/2,y-height/2);
		Point point2 = new Point(x-width/2,y+height/2);
		Point point3 = new Point(x+width/2,y-height/2);
		Point point4 = new Point(x+width/2,y+height/2);
		
		for(int i=0; i<boundaries.size(); i++)
			if(boundaries.get(i).contains(point1) &&
					boundaries.get(i).contains(point2) &&
					boundaries.get(i).contains(point3) &&
					boundaries.get(i).contains(point4))
				return true;
		
		return false;
	}

	public Image toImage() {
//		System.out.println(width*RES + "" + height*RES);
		Image i = new BufferedImage(width*RES,height*RES,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = i.getGraphics();
		
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				g.drawImage(tiles[map[x][y]], x*RES, y*RES, null);
		
		return i;
	}
	
	public void drawOn(LightImage i) {
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				lightTiles[map[x][y]].drawOn(i, x*RES+i.width/2-xOff, y*RES+i.height/2-yOff);
	}

	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
}
