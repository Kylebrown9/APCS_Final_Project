package runner;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Launcher extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	JButton start;
	PicturePanel logo;
	PicturePanel display;
	int value=0;
	
	String[] options = {
			"player.png"
	};
	
	public static void main(String[] args) {
		Launcher l = Launcher.create();
		JFrame j = new JFrame("Launcher");
		j.add(l);
		
		j.setVisible(true);
	}
	
	public static Launcher create() {
		Launcher l = new Launcher();
		l.start.addActionListener(l);
		return l;
	}
	
	private Launcher() {
		this.setLayout(new GridLayout(3,1));
		logo = new PicturePanel();
		logo.setImage("/Resources/Logo.png");
		this.add(logo);
		display = new PicturePanel();
		this.add(display);
		JPanel bottomRow = new JPanel();
		bottomRow.setLayout(new GridLayout(1,3));
		start = new JButton("Start");
		bottomRow.add(start);
		this.add(bottomRow);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(start))
			launchViewer();
	}
	
	public void launchViewer() {
		Dimension dim = new Dimension(800,800);
		
		Viewer v = new Viewer(dim,value);
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
}
