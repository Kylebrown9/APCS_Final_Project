package runner;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Launcher extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Dimension dim = new Dimension(800,800);
		
		Viewer v = new Viewer(dim);
	    v.setVisible(true);  
	    
	    JFrame jF = new JFrame("Dungeon Crawler");
	    jF.add(v);
	    
		jF.setMaximumSize(dim);
		jF.setMinimumSize(dim);
		jF.setPreferredSize(dim);
		jF.setLocationRelativeTo(null);
		jF.setVisible(true);
		jF.setFocusable(true);
	    jF.pack();
	    
	    
	    jF.addMouseListener(v);
	    jF.addComponentListener(v);
	    jF.addKeyListener(v);
	}
	
	public Launcher() {
		this.setLayout(new SpringLayout());
		
	}
}
