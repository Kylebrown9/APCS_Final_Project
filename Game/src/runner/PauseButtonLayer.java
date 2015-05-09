package runner;

import game.LightImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import updaters.PausableUpdater;

public class PauseButtonLayer extends Layer {

	LightImage buttonPause;
	LightImage buttonPlay;
	PausableUpdater updater;
	
	boolean state;
	
	public PauseButtonLayer(PausableUpdater updater) {
		this.updater = updater;
		
		BufferedImage pause = null;
		BufferedImage play = null;
		
		try {
			pause = ImageIO.read(this.getClass().getResource("/Resources/buttonPause.jpg"));
			play = ImageIO.read(this.getClass().getResource("/Resources/buttonPlay.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		buttonPause = new LightImage(pause);
		buttonPlay = new LightImage(play);
	}
	
	@Override
	public boolean inLayer(int x, int y) {
		return x >= 450 && x <= 500 && y >= 0 && y <=100;
	}

	@Override
	public void drawOn(LightImage i) {
		if(updater.playing)
			buttonPause.drawOn(i, 435, 0);
		else 
			buttonPlay.drawOn(i, 435, 0);
	}

	@Override
	public void input(int x, int y) {
		updater.flip();
	}

}
