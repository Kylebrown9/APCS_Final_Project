package runner;

import game.LightImage;

import java.awt.event.KeyEvent;

public abstract class Layer {
	   public boolean active=true;

	   public abstract void drawOn(LightImage i);
	   
	   public abstract boolean inLayer(int x, int y);
	   public abstract boolean acceptsKeys();
	   
	   public abstract void input(int x, int y);
	   public void input(KeyEvent ke) {}
}