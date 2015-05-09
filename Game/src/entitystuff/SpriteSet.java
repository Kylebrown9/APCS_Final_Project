package entitystuff;

import game.LightImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSet {
	LightImage image;
	
	LightImage[][] sprites = new LightImage[21][10];
	
	int res = 64;
	
	public SpriteSet(String path) {
		BufferedImage i = null;
		
		try {
			i = ImageIO.read(this.getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image = new LightImage(i);
		
		for(int row=0; row<21; row++)
			for(int col=0; col<10; col++)
				sprites[row][col] = image.subImage(col*res, row*res, res, res);
	}
	
	public LightImage getSprite(int row, int col) {
		return sprites[row][col];
	}
}
