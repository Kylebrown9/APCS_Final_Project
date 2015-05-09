package entitystuff;

import game.LightImage;
import game.Map;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Entity {	
	Map m;
	protected double x=0, y=0;//, rot;
	protected Point pos;
	
	protected double xMag=0, yMag=0;
	private double speed = 1;
	
	public int width=0,height=0;
	public LightImage image = null;
	
	public Entity(Map m, int x, int y) {
		this.m = m;
		this.x = x;
		this.y = y;
		
		pos = new Point(x,y);
	}
	
	public void setMag(double xVel, double yVel) {
		this.xMag = xVel;
		this.yMag = yVel;
	}
	
	public void update(int time) {
		double dTime = time;
		double newX=x+(dTime*xMag*speed), newY=y+(dTime*yMag*speed);
		
		if(m.inBounds((int)newX,(int)newY,width,height)) {
			x = newX;
			y = newY;
		} else if (m.inBounds(pos.x,(int)newY,width,height)) {
			y = newY;
		} else if (m.inBounds((int)newX,pos.y,width,height)) {
			x = newX;
		}
		
		pos.x = (int) x;
		pos.y = (int) y;
	}
	
	public void drawOn(Graphics g) {
		g.drawImage(image.getImage(), pos.x-m.xOff, pos.y-m.yOff, null);
	}
	
	public void drawOn(LightImage i) {
		image.drawOn(i, pos.x-(image.width/2)+i.width-m.xOff, pos.y-(image.height/2)+i.height-m.yOff);
	}
	
	public int getX() {return pos.x;}
	public int getY() {return pos.y;}
	
	public Point getPos() {
		return pos;
	}
}
