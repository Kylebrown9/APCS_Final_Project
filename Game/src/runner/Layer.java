package runner;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Layer {
	   public boolean active=true;

	   public abstract boolean inLayer(int x, int y);
	   public abstract Image drawOn(Image i);
	   public abstract void drawOn(Graphics g);
	   public abstract void input(int x, int y);
}