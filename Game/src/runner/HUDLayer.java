package runner;

import game.ImageArray;
import game.LightImage;
import game.Player;

import java.awt.event.KeyEvent;

public class HUDLayer extends Layer {
	Player player;
	ImageArray levelNums;
	
	public HUDLayer(Player player) {
		this.player = player;
        String[] paths = {"0-Frame.png","1-Frame.png","2-Frame.png","3-Frame.png","4-Frame.png","5-Frame.png"};
        levelNums = new ImageArray(paths);
	}
	
	public void drawOn(LightImage i) {
		int w=200, h=20;
		i.fillRect(i.width/2-w/2, i.height-100-h, w, h, 16711680);
		i.fillRect(i.width/2-w/2, i.height-100-h, 
				(int)(w*((double)player.getHealth()/(double)player.getMaxHealth())), h, 65280);
		
		i.fillRect(i.width/2-w/2, i.height-75-h, w, h, 25855);
		i.fillRect(i.width/2-w/2, i.height-75-h, 
				(int)(w*((double)player.mana/(double)player.maxMana)), h,225);
		
		i.fillRect(i.width/2-w/2, i.height-50-h, w, h, 16737280);
		i.fillRect(i.width/2-w/2, i.height-50-h, 
				(int)(w*((double)player.getXP()/(double)player.getXPNeeded())), h,16762880);// 16750080);
                
        LightImage level = levelNums.getImage(player.level);
        level.drawOn(i,i.width/2-w/2-level.width-100,i.height-100-h);
	}

	public boolean inLayer(int x, int y) {
		return false;
	}

	public boolean acceptsKey(KeyEvent ke) {
		return false;
	}

	public void input(int x, int y) {}

	public void input(KeyEvent ke) {}

}
