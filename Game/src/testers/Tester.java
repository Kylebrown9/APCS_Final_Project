package testers;

import entitystuff.Entity;
import entitystuff.GameCharacter;
import entitystuff.Player;
import game.Map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import maps.DefaultMap;

public class Tester {
	public static void main(String[] args) {
//		DefaultMapTest();
//		WalkingTest();
		EntityTest();
	}
	
	public static void displayImage(Image i) {
		getPicturePanel().setImage(i);
	}
	
	public static PicturePanel getPicturePanel() {
		PicturePanel p = new PicturePanel();
		JFrame j = new JFrame();
		j.add(p);
		int x=500,y=500;	//width adjustments
		Dimension dim = new Dimension(x,y);
		j.setMaximumSize(dim);
		j.setMinimumSize(dim);
		j.setPreferredSize(dim);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		j.pack();
		
		return p;
	}
	
	public static void EntityTest() {
		PicturePanel p = getPicturePanel();
		
		Map m = new DefaultMap();
		Image i = new BufferedImage(m.getWidth()*Map.RES,m.getHeight()*Map.RES,BufferedImage.TYPE_INT_ARGB);
		Graphics g = i.getGraphics();
		g.drawImage(m.toImage(), 0, 0, null);
		
		Entity player = new Player(m,0,0);
		player.drawOn(g);
		
		Entity player2 = new Player(m,50,10);
		player2.drawOn(g);
		
		p.setImage(i);
	}
	
	public static void WalkingTest() {
		PicturePanel p = getPicturePanel();
		
		Map m = new DefaultMap();
		GameCharacter gC = new Player(m,0,0);
		
		for(int i=0; i<1000; i++){
			gC.update(10);
		
			switch((i%64)/16) {
			case 0:
				gC.setMag(1, 0);
				break;
			case 1:
				gC.setMag(-1, 0);
				break;
			case 2:
				gC.setMag(0, 1);
				break;
			case 3:
				gC.setMag(0, -1);
				break;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			p.setImage(gC.image);
		}
			
		displayImage(m.toImage());
	}
	
	public static void DefaultMapTest() {
		Map m = new DefaultMap();
		
		displayImage(m.toImage());
	}
}
