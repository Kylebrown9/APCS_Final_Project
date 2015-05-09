package runner;

import game.Game;
import game.LightImage;

public class GameLayer extends Layer {
	Game game;

	public GameLayer(Game g) {
		this.game = g;
	}

	public boolean inLayer(int x, int y) {
		return true;
	}

	public void input(int x, int y) {
	    game.input(x,y);
	}

//	public Image drawOn(Image i) {
//		Image newI = new BufferedImage(i.getWidth(null),i.getHeight(null),BufferedImage.TYPE_INT_ARGB);
//		Graphics g = newI.getGraphics();
//		g.drawImage(i, 0, 0, null);
//		g.drawImage(this.game.toImage(), 0, 0, null);
//		return newI;
//	}
//	
//	public void drawOn(Graphics g) {
//		g.drawImage(this.game.toImage(), 0, 0, null);
//	}
	
	public void drawOn(LightImage i) {
		game.drawOn(i);
	}
}
