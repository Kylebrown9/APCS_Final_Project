package runner;

import game.Game;
import game.LightImage;

import java.awt.event.KeyEvent;

public class GameLayer extends Layer {
	Game game;

	public GameLayer(Game g) {
		this.game = g;
	}

	public boolean inLayer(int x, int y) {
		return true;
	}
	
	public boolean acceptsKeys() {
		return true;
	}

	public void input(int x, int y) {
	    game.input(x,y);
	}
	
	public void input(KeyEvent ke) {
		game.input(ke);
	}
	
	public void drawOn(LightImage i) {
		game.drawOn(i);
	}
}
