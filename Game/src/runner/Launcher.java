package runner;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import updaters.Updatable;
import updaters.Updater;
import entitystuff.SpriteSet;
import game.LightImage;

public class Launcher extends JFrame implements ActionListener, Updatable {
	
	private static final long serialVersionUID = 1L;

	JButton start;
	PicturePanel logo;
	PicturePanel display;
	int value=0;
	
	String[] options = {
			"Female_HBlack_WWand.png","Female_HBlue_WDagger.png","Male_HBlond_WSpear.png","Male_HWhite_WBow.png"
	};
	JComboBox<String> skin;
	
	int count=0;
	SpriteSet s;
	Updater u;
	
	public static void main(String[] args) {
		Launcher l = Launcher.create();
		l.setSize(1250, 800);
		l.setVisible(true);
	}
	
	public static Launcher create() {
		Launcher l = new Launcher();
		l.start.addActionListener(l);
		l.skin.addActionListener(l);
//		l.u.run();
		return l;
	}
	
	private Launcher() {
		this.setLayout(new GridLayout(3,1));
		
        //TopRow*****************************************************
		logo = new PicturePanel();
		LightImage logoImage = new LightImage("Logo.png");
		logo.setImage(logoImage.getImage());
		this.add(logo);
		
        //MiddleRow**************************************************
		display = new PicturePanel();
		this.add(display);
        
        ArrayList<Updatable> l = new ArrayList<Updatable>();
        l.add(this);
        u = new Updater(l);
        s = new SpriteSet(options[0]);
		display.setImage(s.getSprite(0, 0).getImage());
        
        //BottomRow**************************************************
		JPanel bottomRow = new JPanel();
		bottomRow.setLayout(new GridLayout(1,3));
		
		skin = new JComboBox<String>(options);
		start = new JButton("Start");
		bottomRow.add(skin);
		bottomRow.add(DumbPanel.create());
		bottomRow.add(start);
		this.add(bottomRow);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(start)) {
			launchViewer();
			u.end();
		}
		
		if(arg0.getSource().equals(skin)) {
			s = new SpriteSet(options[skin.getSelectedIndex()]);
			display.setImage(s.getSprite(0, 0).getImage());
		}
			
	}
	
	@Override
	public void update() {
		display.setImage(s.getSprite(count/16, count%8).getImage());
        count++;
        
        count %= 64;
        repaint();
    }
	
	public void launchViewer() {
		this.setVisible(false);
		
		Dimension dim = new Dimension(800,800);
		Viewer v = new Viewer(dim,skin.getSelectedIndex());
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
