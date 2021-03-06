package entitystuff;

import game.GameMap;
import game.LightImage;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import spells.PassiveEffect;

public abstract class Entity {	
	//*************************Constants*********************************************************
	public static final int CLOSE_ENOUGH  = 1;
	public static final int TYPE_NOCOLLISION  = 0;
	public static final int TYPE_PLAYER  = 1;
	public static final int TYPE_ENEMY  = 2;
	public static final double BASE_SPEED = 1;
	
	public GameMap m;
	
	//
	protected Map<String,PassiveEffect> effects = new TreeMap<String,PassiveEffect>();
	
	//*************************Movement***********************************************************
	protected double x=0, y=0;					//double x and y for calculations
	protected double xMag=0, yMag=0, baseSpeed=1;//directional magnitudes
	public double speed=0;
	
	//*************************Drawing*************************************************************
	protected Point pos;						//integer x and y for drawing
	public LightImage image = null;				//Entities current image
	
	//*************************Collision************************************************************
	public int width=0,height=0;				//Width and height
	private boolean xCol, yCol;					//x and y Collision indicators
	
	//*************************Entity Interaction***************************************************
	List<Entity> entities;
	public int health=10, maxHealth=10;
	public int type = 0;
	protected boolean showHealth=true;
	
	public Entity(GameMap m, int x, int y) {
		this.m = m;
		this.x = x;
		this.y = y;
		
		pos = new Point(x,y);
	}
	
	public void setMag(double xVel, double yVel) {
		this.xMag = xVel;	//sets the directional magnitudes as decimals of the speed
		this.yMag = yVel;
	}
	
	public void add(PassiveEffect e) {
		effects.put(e.toString(), e);
	}
	
	public void update(int time) {
		speed = baseSpeed;
		
		//*****************************************PassiveEffects**********************************
		List<PassiveEffect> currentEffects = new ArrayList<PassiveEffect>(effects.values());
		for(int i=0; i<currentEffects.size(); i++) {
			currentEffects.get(i).update();
		}
		
		for(int i=0; i<currentEffects.size(); i++) {
			if(currentEffects.get(i).isDone()) {
				effects.remove(currentEffects.get(i).toString());
			}
		}
		
		//*****************************************Movement*****************************************
		//Calculate New Position
		double dTime = time;
		double newX=x+(dTime*xMag*speed), newY=y+(dTime*yMag*speed);
		
		//Boundary Collision Checks
		if(m.inBounds((int)newX,(int)newY,width,height)) {
			x = newX;
			y = newY;
			
			xCol = false;
			yCol = false;
		} else if (m.inBounds(pos.x,(int)newY,width,height)) {
			y = newY;
			
			xCol = true;
			yCol = false;
		} else if (m.inBounds((int)newX,pos.y,width,height)) {
			x = newX;
			
			xCol = false;
			yCol = true;
		}
		
		//Set position for drawing
		pos.x = (int) x;
		pos.y = (int) y;
	}
	
	public void drawOn(Graphics g) {
		g.drawImage(image.getImage(), pos.x-m.xOff, pos.y-m.yOff, null);
	}
	
	public void drawOn(LightImage i) {
		int topLX=pos.x+i.width/2-m.xOff-image.width/2, 
				topLY=pos.y+i.height/2-m.yOff-image.height/2;
		
		if(showHealth) {
			int hBW=20, hBH=5;
			i.fillRect(topLX+image.width/2-hBW/2, (int)topLY+50+hBH, hBW, hBH, 16711680);
			i.fillRect(topLX+image.width/2-hBW/2, (int)topLY+50+hBH, (int)(hBW*((double)health/(double)maxHealth)), hBH, 65280);
		}
			
		image.drawOn(i, topLX, topLY);
	}
	//***********************Health****************************************
	public void damage(int damage) {
		health -= damage;
	}
	
	public void heal(int healing) {
		if(health+healing > maxHealth)
			health = maxHealth;
		else
			health += healing;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	//**********************************************************************
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
	
	public boolean xCollision() {return xCol;}
	public boolean yCollision() {return yCol;}
	public Point getPos() {return pos;}
	public int getX() {return pos.x;}
	public int getY() {return pos.y;}
}
