package runner;

import game.Game;
import game.LightImage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import updaters.PausableUpdater;
import updaters.Updatable;

public class Viewer extends JPanel implements MouseListener, Updatable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Viewer v = new Viewer();
	    v.setVisible(true);  
	    
	    JFrame jF = new JFrame("Dungeon Crawler");
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
	}

	List<Layer> layers;
	Game g;

	public Viewer() {
	   layers = new ArrayList<Layer>();
	   g = new Game();
	   
	   PausableUpdater p = new PausableUpdater(g,this);
	   
	   PauseButtonLayer pauseLayer = new PauseButtonLayer(p);
	   GameLayer gameLayer = new GameLayer(g);
	   
	   layers.add(pauseLayer);
	   layers.add(gameLayer);
	}
	   
	public void paintComponent(Graphics g) {
	   LightImage lI = LightImage.newLightImage();
	   
	   for(int i= layers.size()-1; i>=0; i--)
	      if(layers.get(i).active)
	         layers.get(i).drawOn(lI);
	   
	   g.drawImage(lI.getImage(),0,0,null);
	}

	@Override
	public void mousePressed(MouseEvent me) {
		for(int i=0; i<layers.size(); i++) {
			if(layers.get(i).inLayer(me.getX(), me.getY())) {
				layers.get(i).input(me.getX(), me.getY());
				return;
			}
		}
		
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

	@Override
	public void update() {
		repaint();
	}
}
