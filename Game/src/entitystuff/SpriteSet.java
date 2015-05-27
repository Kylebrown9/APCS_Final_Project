package entitystuff;

import game.LightImage;

public class SpriteSet {
	LightImage image;
	
	LightImage[][] sprites = new LightImage[21][10];
	
	int res = 64;
	
	public SpriteSet(String path) {
		image = new LightImage(path);
		
		for(int row=0; row<21; row++)
			for(int col=0; col<10; col++)
				sprites[row][col] = image.subImage(col*res, row*res+10, res, res-10);
	}
	
	public LightImage getSprite(int row, int col) {
		return sprites[row][col];
	}
}
