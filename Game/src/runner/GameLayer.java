package runner;

import game.Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameLayer extends Layer {
	Game g;

	public GameLayer(Game g) {
		this.g = g;
	}

	public boolean inLayer(int x, int y) {
		return true;
	}

	public void input(int x, int y) {
	    g.input(x,y);
	}

	public Image drawOn(Image i) {
		Image newI = new BufferedImage(i.getWidth(null),i.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = newI.getGraphics();
		g.drawImage(i, 0, 0, null);
		g.drawImage(this.g.toImage(), 0, 0, null);
		return newI;
	}
	
	public void drawOn(Graphics g) {
		g.drawImage(this.g.toImage(), 0, 0, null);
	}
}
