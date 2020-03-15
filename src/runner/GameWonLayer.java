package runner;

import game.LightImage;

import java.awt.event.KeyEvent;

public class GameWonLayer extends Layer {
	
	LightImage image;
	
	public GameWonLayer(){
		image = new LightImage("gameWon.png");
		active = false;
	}

	@Override
	public void drawOn(LightImage i) {
		image.drawOnCentered(i);
	}

	@Override
	public boolean inLayer(int x, int y) {
		return false;
	}

	@Override
	public boolean acceptsKey(KeyEvent ke) {
		return false;
	}

	@Override
	public void input(int x, int y) {}

	@Override
	public void input(KeyEvent ke) {}
}
