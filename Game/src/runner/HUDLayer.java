package runner;

import game.LightImage;
import game.Player;

public class HUDLayer extends Layer {
	Player player;
	
	public HUDLayer(Player player) {
		this.player = player;
	}
	
	public void drawOn(LightImage i) {
		int hBW=200, hBH=20;
		i.fillRect(i.width/2-hBW/2, i.height-100-hBH, hBW, hBH, 16711680);
		i.fillRect(i.width/2-hBW/2, i.height-100-hBH, 
				(int)(hBW*((double)player.getHealth()/(double)player.getMaxHealth())), hBH, 65280);
		
		
		int mBW=200, mBH=20;
		i.fillRect(i.width/2-mBW/2, i.height-75-mBH, mBW, mBH, 25855);
		i.fillRect(i.width/2-mBW/2, i.height-75-mBH, 
				(int)(mBW*((double)player.mana/(double)player.maxMana)), mBH,225);
		
		int xBW=200, xBH=20;
		i.fillRect(i.width/2-xBW/2, i.height-50-xBH, xBW, xBH, 16737280);
		i.fillRect(i.width/2-xBW/2, i.height-50-xBH, 
				(int)(xBW*((double)player.getXP()/(double)player.getXPNeeded())), xBH,16762880);// 16750080);
	}

	public boolean inLayer(int x, int y) {
		return false;
	}

	public boolean acceptsKeys() {
		return false;
	}

	public void input(int x, int y) {}
}
