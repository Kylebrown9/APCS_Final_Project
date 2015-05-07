package runner;

import game.Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maps.DefaultMap;

public class Viewer extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Viewer v = new Viewer();
	    v.setVisible(true);  
	    
	    JFrame jF = new JFrame();
	    jF.add(v);
	    
		Dimension dim = new Dimension(500,500);
		jF.setMaximumSize(dim);
		jF.setMinimumSize(dim);
		jF.setPreferredSize(dim);
		jF.setLocationRelativeTo(null);
		jF.setVisible(true);
		jF.setFocusable(true);
	    jF.pack();
	    
	    jF.addMouseListener(v);
	    
//	    try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	    v.repaint();
	}

	List<Layer> layers;
	Game g;

	public Viewer() {
	   layers = new ArrayList<Layer>();
	   g = new Game(new DefaultMap());
	   layers.add(new GameLayer(g));
	}
	   
	public void paintComponent(Graphics g) {
	   Image screen = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
	   Graphics gA = screen.getGraphics();

//	   for(int i= layers.size()-1; i>0; i--) {
//	      if(layers.get(i).active)
//	         screen = layers.get(i).drawOn(screen);
//	      }

	   layers.get(0).drawOn(screen);
	   
	   g.drawImage(screen,0,0,null);
	}

	@Override
	public void mousePressed(MouseEvent me) {
//		for(int i=0; i<layers.size(); i++) {
//			if(layers.get(i).inLayer(me.getX(), me.getY())) {
//				layers.get(i).input(me.getX(), me.getY());
//				return;
//			}
//		}

		layers.get(0).input(me.getX(), me.getY());
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
}
