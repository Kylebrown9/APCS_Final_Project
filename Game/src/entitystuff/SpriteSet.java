package entitystuff;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class SpriteSet {
	BufferedImage image;
	int res = 65;
	
	public SpriteSet(BufferedImage i) {
		image = i;
	}
	
	public Image getWalkingSprite(int dir, int step) {
		return getSprite(dir+8,step);
	}
	
	public Image getSprite(int row, int col) {
		return image.getSubimage(col*(res-1), row*(res), res, res);
	}
}
