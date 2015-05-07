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

public class Map implements Drawable {
	public static final int RES = 25;
	
	protected int[][] map;
	private int width, height;
	private Image[] tiles;
	protected List<Rectangle> boundaries;
	public int xOff=0, yOff=0;
	
	public List<Entity> npcs = new ArrayList<Entity>();
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new int[width][height];
		
		String[] paths = {
				"grass0B.png",
				"stone2B.png",
				"stone2B.png",
				"wood1B.png",
				"carpet0B.jpg",
				"step0B.jpg"
		};
		
		tiles = new Image[paths.length];
		
		boundaries = new ArrayList<Rectangle>();
		
		for(int i=0; i<paths.length; i++) {
			try {
//				return ImageIO.read(getClass().getResource("/Resources/" + path));
				tiles[i] = ImageIO.read(this.getClass().getResource("/Resources/" + paths[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void setRect(int type, int x, int y, int width, int height) {
		for(int i=x; i<x+width; i++)
			for(int j=y; j<y+height; j++)
				if(i > 0 && i < this.width && j > 0 && j < this.height)
					map[i][j] = type;
	}
	
	public boolean inBounds(int x, int y) {
		Point point = new Point(x,y);
		
		for(int i=0; i<boundaries.size(); i++)
			if(boundaries.get(i).contains(point))
				return true;
		
		return false;
	}

	@Override
	public Image toImage() {
//		System.out.println(width*RES + "" + height*RES);
		Image i = new BufferedImage(width*RES,height*RES,BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = i.getGraphics();
		
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				g.drawImage(tiles[map[x][y]], x*RES, y*RES, null);
		
		return i;
	}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
}
