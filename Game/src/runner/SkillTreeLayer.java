package runner;

import game.ImageArray;
import game.LightImage;
import game.Player;
import game.PlayerSkill;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import spells.BouncingBallSpell;
import spells.FireBallSpell;
import spells.MagicBoltSpell;
import spells.OrbitBallSpell;
import spells.SnowBallSpell;

public class SkillTreeLayer extends Layer{

	public static final int AVAILABLE=0;
	public static final int UNLOCKED=1;
	public static final int LOCKED=2;
	
	Player p;
	SkillTree t;
	ArrayList<Rectangle> selectAreas;
	final LightImage BACKGROUND=new LightImage("background.png"); 
	final LightImage U_FRAME=new LightImage("UnlockedFrame.png"),
			A_FRAME=new LightImage("AvailFrame.png"),
			L_FRAME=new LightImage("LockedFrame.png"),
			STAR=new LightImage("Star.png");
	final int FRAME_DIM= 100;
	
	int[] frameX = {50,-50,-50,-50,150,150};
	int[] frameY = {60,180,300,420,180,300};
	int[] imageX = {90,-10,-10,-10,190,190};
	int[] imageY = {100,220,340,460,220,340};
	String[] paths = {"MagicBall.png","fireball.png","BouncyFire.png","OribtFire1.png","snowball.png","poisonball.png"};
	ImageArray icons = new ImageArray(paths);
	String[] numberPaths = {"0-Frame.png","1-Frame.png","2-Frame.png","3-Frame.png","4-Frame.png","5-Frame.png"};
	ImageArray numbers = new ImageArray(numberPaths);
	int xOffset=0,yOffset=0,tLX=0,tLY=0;
	Dimension dim;
	
	public SkillTreeLayer(Player player){
		p=player;
		
		ArrayList<PlayerSkill> temp = new ArrayList<PlayerSkill>();
		temp.add(new PlayerSkill("Magic Ball",null,SkillTree.MAGIC_BALL));
		temp.add(new PlayerSkill("Fireball",temp.get(0),SkillTree.FIREBALL));
		temp.add(new PlayerSkill("Bouncy Fire",temp.get(1),SkillTree.BOUNCY_FIRE));
		temp.add(new PlayerSkill("Orbit Fire",temp.get(2),SkillTree.ORBIT_FIRE));
		temp.add(new PlayerSkill("Snowball",temp.get(0),SkillTree.SNOWBALL));
		temp.add(new PlayerSkill("Poisonball",temp.get(4),SkillTree.POISONBALL));
		t=new SkillTree(temp);
	}
	
	public void setDim(Dimension dim) {
		selectAreas = new ArrayList<Rectangle>();
		this.dim = dim;
		tLX = (dim.width-BACKGROUND.width)/2;
		tLY = (dim.height-BACKGROUND.height)/2;
		xOffset = dim.width/2-100;
		yOffset = (dim.height-BACKGROUND.height)/2;
		for(int k=0; k<frameX.length; k++)
			selectAreas.add(new Rectangle(frameX[k]+xOffset,frameY[k]+yOffset,FRAME_DIM,FRAME_DIM));
	}
	
	
	public void drawOn(LightImage i) {
		BACKGROUND.drawOnCentered(i);
		numbers.getImage(p.skillPoints).drawOn(i,(i.width+BACKGROUND.width)/2-110,yOffset+50);
		
		LightImage frameBackground;
		for(int k=0; k<paths.length; k++) {
			if(t.getSkill(k).isUnlocked())
				frameBackground = U_FRAME;
			else
				if(t.getSkill(k).isAvail())
					frameBackground = A_FRAME;
				else
					frameBackground = L_FRAME;
			
			frameBackground.drawOn(i,frameX[k]+xOffset,frameY[k]+yOffset);
			icons.getImage(k).drawOn(i,imageX[k]+xOffset,imageY[k]+yOffset);
		}
	}
	
	public boolean inLayer(int x, int y) {
		return x>=tLX&&x<BACKGROUND.width+tLX&&y>=tLY&&y<BACKGROUND.height+tLY;
	}

	public boolean acceptsKey(KeyEvent ke) {
		return ke.getKeyCode() == KeyEvent.VK_SPACE;
	}

	public void input(int x, int y) {
		if(!inLayer(x,y))
			return;
		
		Point point = new Point(x,y);
		
		int i=1;
		
		if(p.skillPoints <= 0)
			return;
		if(selectAreas.get(0).contains(point)){
			t.unlockSkill(t.getSkill(0));
			p.skills[0]=new MagicBoltSpell(p,t.getSkill(0).getLevel());
		} else if(selectAreas.get(1).contains(point)){
			t.unlockSkill(t.getSkill(1));
			p.skills[1]=new FireBallSpell(p,t.getSkill(1).getLevel());
		} else if(selectAreas.get(2).contains(point)){
			t.unlockSkill(t.getSkill(2));
			p.skills[2]=new BouncingBallSpell(p,t.getSkill(2).getLevel());
		} else if(selectAreas.get(3).contains(point)){
			t.unlockSkill(t.getSkill(3));
			p.skills[3]=new OrbitBallSpell(p,t.getSkill(4).getLevel());
		} else if(selectAreas.get(4).contains(point)){
			t.unlockSkill(t.getSkill(4));
			p.skills[4]=new SnowBallSpell(p,t.getSkill(3).getLevel());
		} else if(selectAreas.get(5).contains(point)){
			t.unlockSkill(t.getSkill(5));
			p.skills[5]=new FireBallSpell(p,t.getSkill(5).getLevel());
		} else {
			i=0;
			return;
		}
		p.skillPoints -= i;
		
		t.updateTree();
	}

	@Override
	public void input(KeyEvent ke) {
		active = !active;
	}
}
