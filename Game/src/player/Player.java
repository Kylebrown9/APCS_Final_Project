package player;

import entitystuff.PlayerCharacter;

public class Player {
	
	PlayerCharacter pC;
	Spell[] skills;
	
	public void setPC(PlayerCharacter pC) {
		this.pC = pC;
		
		Spell[] skillset = {
				new FireBallSpell(pC,1)
		};
		
		skills = skillset;
	}
	
	public void cast(int skill, int x, int y) {
		skills[skill].cast(x,y);
	}
}
