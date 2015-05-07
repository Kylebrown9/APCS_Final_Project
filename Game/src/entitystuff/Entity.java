package entitystuff;

import game.Map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Entity {	
	Map m;
	private int x=0, y=0;//, rot;
	protected double xMag=0, yMag=0;
	private double speed = 10;
	public Image image;
	
	public Entity(Map m, Image image, int x, int y) {
		this.m = m;
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void setMag(double xVel, double yVel) {
		this.xMag = xVel;
		this.yMag = yVel;
	}
	
	public void setTrajectory(double angle) {
		this.xMag = Math.cos(angle);
		this.yMag = Math.sin(angle);
	}
	
	public void update(int time) {
		int newX=x+(int)(time*xMag*speed), newY=y+(int)(time*yMag*speed);
		
		if(m.inBounds(newX,newY)) {
			x = newX;
			y = newY;
		}
	}
	
	public Image drawOn(Image i) {
//		i.getGraphics().drawImage(ImageStuff.getRotatedImage(image, rot), 0, 0, null);
		Image newI = new BufferedImage(i.getWidth(null),i.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = newI.getGraphics();
		
		g.drawImage(i, 0, 0, null);
		g.drawImage(image, x-m.xOff, x-m.yOff, null);
		return newI;
	}
	
	public void drawOn(Graphics g) {
		g.drawImage(image, x-m.xOff, x-m.yOff, null);
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
}
