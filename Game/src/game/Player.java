package game;

import spells.FireBallSpell;
import spells.SnowBallSpell;
import spells.Spell;
import entitystuff.PlayerCharacter;

public class Player {
	public static final int NOSPELL = 10;
	
	PlayerCharacter pC;
	Spell[] skills;
	
	boolean casting;
	int spellID = NOSPELL;
	
	public Player(PlayerCharacter pC) {
		Spell[] skillset = {
				new FireBallSpell(pC,1),
				new SnowBallSpell(pC,1),
				new FireBallSpell(pC,1),
				new FireBallSpell(pC,1)
		};
		
		skills = skillset;
		casting = false;
		
		setPC(pC);
	}
	
	public void setPC(PlayerCharacter pC) {
		this.pC = pC;
	}
	
	public void input(int x, int y) {
		if(casting) {
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
}
