package runner;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PicturePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Image image;
	
	public PicturePanel() {
		image = null;
	}
	
	public void setImage(Image image) {
		this.image = image;
		repaint();
	}
	
	public void setImage(String path) {
		try {
			image = ImageIO.read(this.getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
