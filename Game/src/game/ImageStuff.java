package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageStuff {
	public static Image getRotatedImage(Image i, int angle)
	{
		Image newImage = new BufferedImage(i.getWidth(null),i.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)newImage.getGraphics().create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform original = g2.getTransform();
		
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle));
		g2.setTransform(at);
		g2.drawImage(i, 0, 0, null);
		g2.setTransform(original);
		
		return newImage;
	}
}