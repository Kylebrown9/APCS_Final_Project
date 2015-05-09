package runner;

import game.LightImage;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import updaters.PausableUpdater;

public class PauseButtonLayer extends Layer {

	LightImage pauseButton;
	LightImage playButton;
	PausableUpdater updater;
	
	boolean state;
	
	Dimension windowDim;
	
	public PauseButtonLayer(PausableUpdater updater) {
		this.updater = updater;
		
		BufferedImage pause = null;
		BufferedImage play = null;
		
		try {
			pause = ImageIO.read(this.getClass().getResource("/Resources/pauseButton.png"));
			play = ImageIO.read(this.getClass().getResource("/Resources/playButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		pauseButton = new LightImage(pause);
		playButton = new LightImage(play);
	}
	
	@Override
	public boolean inLayer(int x, int y) {
		return x >= windowDim.width-50 && x <= windowDim.width && y >= 0 && y <=100;
	}
	
	public boolean acceptsKeys() {
		return false;
	}

	@Override
	public void drawOn(LightImage i) {
		if(updater.playing)
			pauseButton.drawOn(i, windowDim.width-50, 0);
		else 
			playButton.drawOn(i, windowDim.width-50, 0);
	}

	@Override
	public void input(int x, int y) {
		updater.flip();
	}
	
	public void setDim(Dimension dim) {
		windowDim = dim;
	}

}
