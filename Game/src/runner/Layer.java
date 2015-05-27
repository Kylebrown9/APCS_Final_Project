package runner;

import game.LightImage;

import java.awt.event.KeyEvent;

public abstract class Layer {
	   public boolean active=true;

	   public abstract void drawOn(LightImage i);
	   
	   public abstract boolean inLayer(int x, int y);
	   public abstract boolean acceptsKey(KeyEvent ke);
	   
	   public abstract void input(int x, int y);
	   public abstract void input(KeyEvent ke);
}