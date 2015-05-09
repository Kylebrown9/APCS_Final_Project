package testers;

import entitystuff.Entity;
import entitystuff.Character;
import entitystuff.PlayerCharacter;
import game.Game;
import game.LightImage;
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
//		WalkingInPlaceTest();
//		JavaImageTest();
//		LightImageTest();
//		DrawGameTest();
		MovementTest();
	}
	
	public static void displayImage(Image i) {
		getPicturePanel().setImage(i);
	}
	
	public static PicturePanel getPicturePanel(String name) {
		PicturePanel p = new PicturePanel();
		JFrame j = new JFrame(name);
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
	
	public static PicturePanel getPicturePanel() {
		return getPicturePanel("");
	}
	
	public static void MovementTest() {
		PicturePanel p = getPicturePanel("MovementTest");
		Game g = new Game();
		
		LightImage i = new LightImage(new Dimension(500,500));
		
		g.drawOn(i);
		
		g.p.setMag(1, 0);
		
		for(int a=0; a<1000; a++) {
			g.p.update(1);
			g.drawOn(i);
			p.setImage(i.getImage());
			delay(10);
			
			if(a > 750)
				g.p.setMag(0, -1);
			else if(a > 500)
				g.p.setMag(-1, 0);
			else if(a > 250)
				g.p.setMag(0, 1);
			
			
		}
	}
	
	public static void DrawGameTest() {
		PicturePanel p = getPicturePanel("GameTest");
		Game g = new Game();
		
		LightImage i = new LightImage(new Dimension(500,500));
		
		g.drawOn(i);
		
		p.setImage(i.getImage());
	}

	//This tests layering two entities on top of a map using the LightImage system
	public static void LightImageTest() {
		PicturePanel p = getPicturePanel("LightImageTest");
		
		Map m = new DefaultMap();
		LightImage i = new LightImage(new Dimension(500,500));
		m.drawOn(i);
		
		Entity player = new PlayerCharacter(m,0,0);
		player.drawOn(i);
		
		Entity player2 = new PlayerCharacter(m,50,10);
		player2.drawOn(i);
		
		p.setImage(i.getImage());
	}
	
	//This tests layering two entities on top of a map using the graphics system
	public static void JavaImageTest() {
		PicturePanel p = getPicturePanel("JavaImageTest");
		
		Map m = new DefaultMap();
		Image i = new BufferedImage(m.getWidth()*Map.RES,m.getHeight()*Map.RES,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = i.getGraphics();
		g.drawImage(m.toImage(), 0, 0, null);
		
		Entity player = new PlayerCharacter(m,0,0);
		player.drawOn(g);
		
		Entity player2 = new PlayerCharacter(m,50,10);
		player2.drawOn(g);
		
		p.setImage(i);
	}
	
	public static void WalkingInPlaceTest() {
		PicturePanel p = getPicturePanel("WalkingInPlaceTest");
		
		Map m = new DefaultMap();
		Character gC = new PlayerCharacter(m,0,0);
		
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
			
			delay(100);
			
			p.setImage(gC.image.getImage());
		}
			
		displayImage(m.toImage());
	}
	
	public static void DefaultMapTest() {
		Map m = new DefaultMap();
		
		displayImage(m.toImage());
	}
	
	public static void delay(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
