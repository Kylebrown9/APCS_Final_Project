package runner;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.LightImage;

public class GameOverLayer extends Layer {

	LightImage image;
	
	public GameOverLayer() {
		BufferedImage i = null;
		
		try {
			i = ImageIO.read(this.getClass().getResource("/Resources/pauseButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image = new LightImage(i);
	}
	
	@Override
	public void drawOn(LightImage i) {
		image.drawOnCentered(i);
	}

	@Override
	public boolean inLayer(int x, int y) {
		return true;
	}

	@Override
	public boolean acceptsKeys() {
		return false;
	}

	@Override
	public void input(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
