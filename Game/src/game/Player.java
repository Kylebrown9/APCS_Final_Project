package game;

import java.util.ArrayList;
import java.util.List;

import spells.BouncingBallSpell;
import spells.FireBallSpell;
import spells.SnowBallSpell;
import spells.Spell;
import updaters.Updatable;
import entitystuff.PlayerCharacter;

public class Player implements Updatable {
	public static final int NOSPELL = 10;
	public static final double BASEHEALTH = 100;
	
	public PlayerCharacter pC;
	Spell[] skills;
	
	boolean casting;
	int spellID = NOSPELL;
	public int mana=100, baseMana=100, maxMana=100, manaRate=80, count=0;
	
	int xP=0, xPNeeded, level=1, skillPoints=1;
	
	List<Attribute> attributes = new ArrayList<Attribute>();
	
	public Player() {
		casting = false;
		
		xP = xPNeededForLvl(1);
		xPNeeded = xPNeededForLvl(2);
	}
	
	public void applyAttributes() {
		double healthMod=1, speedMod=1, manaMod=1;
		
		for(Attribute a : attributes){
			switch(a.getEffectType()){
				case "health":
					healthMod	+= a.getEffectAmount();
					break;
				case "speed":
					speedMod	+= a.getEffectAmount();
					break;
				case "mana":
					manaMod	+= a.getEffectAmount();
					break;
			}
		}
		
		maxMana = (int)((double)(baseMana)*(1+manaMod));
		pC.modifyBaseSpeed(speedMod);
		pC.setMaxHealth((int)(BASEHEALTH*healthMod));
	}
	
	public void setPC(PlayerCharacter pC) {
		this.pC = pC;
		
		Spell[] skillset = {
				new FireBallSpell(this,1),
				new SnowBallSpell(this,1),
				new BouncingBallSpell(this,1),
				new FireBallSpell(this,1)
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
		return (int)Math.pow(10, level+1);
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
