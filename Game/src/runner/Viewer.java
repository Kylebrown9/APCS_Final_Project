package runner;

import game.Game;
import game.LightImage;
import game.Player;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import updaters.PausableUpdater;
import updaters.Updatable;

public class Viewer extends JPanel implements MouseListener, KeyListener, ComponentListener, Updatable {

	private static final long serialVersionUID = 1L;

	Dimension dim;
	
	List<Layer> layers;
	Player p;
	Game g;
	
	PauseButtonLayer pauseLayer;

	public Viewer(Dimension dim) {
		this.dim = dim;
		
		layers = new ArrayList<Layer>();
		g = new Game();
	   
		PausableUpdater p = new PausableUpdater(g,this);
	   
		pauseLayer = new PauseButtonLayer(p);
		GameLayer gameLayer = new GameLayer(g);
	   
		pauseLayer.setDim(dim);
		g.setDim(dim);
		
		layers.add(pauseLayer);
		layers.add(gameLayer);
	}
	   
	public void paintComponent(Graphics g) {
	   LightImage lI = new LightImage(dim);
	   
	   for(int i= layers.size()-1; i>=0; i--)
		   if(layers.get(i).active)
	    	  layers.get(i).drawOn(lI);
	   
	   	g.drawImage(lI.getImage(),0,0,null);
	}

	@Override
	public void mousePressed(MouseEvent me) {
		for(int i=0; i<layers.size(); i++) {
			if(layers.get(i).inLayer(me.getX(), me.getY()) && layers.get(i).active) {
				layers.get(i).input(me.getX(), me.getY());
				return;
			}
		}
		
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {	
		for(int i=0; i<layers.size(); i++) {
			if(layers.get(i).acceptsKeys() && layers.get(i).active) {
				layers.get(i).input(arg0);
				return;
			}
		}
		
		repaint();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		dim = getSize();
		
		pauseLayer.setDim(dim);
		g.setDim(dim);
	}
	
	@Override
	public void update() {
		repaint();
	}
	
	//**********************************************************************************************
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent e) {}

	//**********************************************************************************************

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	//**********************************************************************************************
	@Override
	public void componentHidden(ComponentEvent arg0) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}

	
}
