package game;

import spells.MagicBoltSpell;
import spells.NoSpell;
import spells.Spell;
import updaters.Updatable;
import entitystuff.PlayerCharacter;

public class Player implements Updatable {
	public static final int NOSPELL = 10;
	public static final double BASEHEALTH = 100;
	
	public PlayerCharacter pC;
	public Spell[] skills;
	
	boolean casting;
	int spellID = NOSPELL;
	public int mana=100, baseMana=100, maxMana=100, manaRate=40, count=0;
	public int sightRange = 1000;
	
	int xP=0, xPNeeded;
	public int level=1, skillPoints=1;
	
	public Player() {
		casting = false;
		
		xP = xPNeededForLvl(1);
		xPNeeded = xPNeededForLvl(2);
	}
	
	public void setPC(PlayerCharacter pC) {
		this.pC = pC;
		
		Spell[] skillset = {
				new MagicBoltSpell(this,1),
				new NoSpell(pC.m,this),
				new NoSpell(pC.m,this),
				new NoSpell(pC.m,this),
				new NoSpell(pC.m,this),
				new NoSpell(pC.m,this)
		};
		
		skills = skillset;
	}
	
	public void input(int x, int y) {
		if(!casting) {
			pC.setTargetRelative(x, y);
		} else {
			pC.setTargetRelative(0, 0);
			cast(x,y);
		}
	}
	
	public void startCast(int spellID) {
		if(spellID == NOSPELL)
			return;
		
		this.spellID = spellID;
		casting = true;
	}
	
	public void cast(int x, int y) {
		if(casting && spellID != NOSPELL)
			skills[spellID].cast(x,y);
		
		casting = false;
	}
	
	public void giveXP(int amount) {
		xP += amount;
		
		if(xP >= xPNeeded) {
			skillPoints++;
			level++;
			xPNeeded = xPNeededForLvl(level+1); 
		}
	}
	
	public int xPNeededForLvl(int level) {
		return 100*(level+1)*(level+1);
	}
	
	public int getXP() 			{return xP;}
	public int getXPNeeded()	{return xPNeeded;}
	public int getHealth() 		{return pC.health;}
	public int getMaxHealth()	{return pC.maxHealth;}

	@Override
	public void update() {
		if(count % manaRate == 0)
			if(mana == maxMana)
				return;
			else
				mana++;
		
		count++;
	}
}
