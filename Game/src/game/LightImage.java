package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class LightImage {
	private BufferedImage image;
	private int[] pixelsI;
	public int width, height;
	
	public static final int TRANSPARENT=16711900;	//the int code for R:255 G:0 B:220, pink
	public static final int TRANSPARENT1=16777215;
	
	public LightImage(Dimension dim) {
		width = dim.width;
		height = dim.height;
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		pixelsI = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public LightImage(BufferedImage i) {
		width = i.getWidth();
		height = i.getHeight();
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose();
		g = null;
		
		pixelsI = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public static LightImage imageAtPath(String path) {
		BufferedImage i=null;
		try {
			i = ImageIO.read(LightImage.class.getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new LightImage(i);
	}
	
	public void drawOn(LightImage i, int xS, int yS) {
		int color;
		
		for		(int x=0; 	(x<width) && (x+xS < i.width); 	x++)
			for	(int y=0; 	(y<height) && (y+yS < i.height);	y++){
				color = this.getColor(x, y);
				if(y+yS >= 0 && x+xS >= 0)
					if(color != LightImage.TRANSPARENT && color != LightImage.TRANSPARENT1)
						i.setColor(x+xS,y+yS,color);
			}
					
	}
	
	public void drawOnNoAlpha(LightImage i, int xS, int yS) {
		for		(int x=0; 	(x<width) && (x+xS < i.width); 	x++)
			for	(int y=0; 	(y<height) && (y+yS < i.height);	y++)
				if(y+yS >= 0 && x+xS >= 0)
					i.setColor(x+xS,y+yS,this.getColor(x, y));
	}
	
	public void drawOn(LightImage i) {
		drawOn(i,0,0);
	}
	
	public void drawOnCentered(LightImage i) {
		drawOn(i,(i.width-this.width)/2,(i.height-this.height/2));
	}
	
	public void drawLineOn(Point p1, Point p2) {
		int m = (p1.y-p2.y)/(p1.x-p2.x);
		int b = p1.y-m*p1.x;
		for(int x=p1.x; x<p2.x; x++) {
			setColor(x,m*x+b,0);
		}
	}
	
	public void fillRect(int x, int y, int width, int height, int color) {
		for		(int i=(x<0?0:x); i<(x+width) && i<this.width;  i++)
			for	(int j=(y<0?0:y); j<(y+height)&& j<this.height; j++)
				setColor(i,j,color);
	}
	
	public LightImage subImage(int x, int y, int width, int height) {
		BufferedImage i = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		LightImage lI = new LightImage(i);
		
		drawOnNoAlpha(lI,-x,-y);
		
		return lI;
	}
	
	public int getColor(int x, int y) {
		return pixelsI[y*width+x];
	}
	
	public void setColor(int x, int y, int color) {
		pixelsI[y*width+x] = color;
	}
	
	public List<Point> getPointsWithColor(int color) {
		List<Point> output = new ArrayList<Point>();
		
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				if(getColor(x,y) == color)
					output.add(new Point(x,y));
		
		return output;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
