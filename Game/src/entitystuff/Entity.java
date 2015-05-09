package entitystuff;

import game.LightImage;
import game.Map;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Entity {	
	public static final int CLOSE_ENOUGH  = 1;
	
	public Map m;
	protected double x=0, y=0;//, rot;
	protected Point pos;
	
	protected double xMag=0, yMag=0;
	protected double speed = 1;
	
	public int width=0,height=0;
	public LightImage image = null;
	
	public int health=10;
	public int type = 0;
	
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
		image.drawOn(i, pos.x+i.width/2-m.xOff, pos.y+i.height/2-m.yOff);
	}
	
	public void damage(int damage) {
		health -= damage;
	}
	
	public double distTo(Point dest) {
		double xDiff = dest.x-x;
		double yDiff = dest.y-y;
		
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	
	public void moveTowards(Point dest, double dist) {
		if(dist < CLOSE_ENOUGH)
			this.setMag(0, 0);
		else
			this.setMag((dest.x-x)/dist, (dest.y-y)/dist);
	}
	
	public Point getPos() {return pos;}
	public int getX() {return pos.x;}
	public int getY() {return pos.y;}
}
