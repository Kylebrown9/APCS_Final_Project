package runner;

import game.LightImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import updaters.PausableUpdater;

public class PauseButtonLayer extends Layer {

	LightImage button;
	PausableUpdater updater;
	
	public PauseButtonLayer(PausableUpdater updater) {
		this.updater = updater;
		
		BufferedImage i = null;
		
		try {
			i = ImageIO.read(this.getClass().getResource("/Resources/button.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		button = new LightImage(i);
	}
	
	@Override
	public boolean inLayer(int x, int y) {
		return x >= 450 && x <= 500 && y >= 0 && y <=100;
	}

	@Override
	public void drawOn(LightImage i) {
		button.drawOn(i, 450, 0);
	}

	@Override
	public void input(int x, int y) {
		updater.flip();
	}

}
