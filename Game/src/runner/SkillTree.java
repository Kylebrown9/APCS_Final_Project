package runner;
import java.util.*;
import game.PlayerSkill;


public class SkillTree {
	private List<PlayerSkill> playerSkills;
	public static final int MAGIC_BALL=0,
			FIREBALL=1,
			SNOWBALL=2,
			POISONBALL=3,
			BOUNCY_FIRE=4,
			ORBIT_FIRE=5;
	
	public SkillTree()
	{
		playerSkills=new ArrayList<PlayerSkill>(0);
	}
	
	public SkillTree(List<PlayerSkill> skills)
	{
		playerSkills=skills;
	}
	
	/**
	 * Updates all skills in the tree
	 */
	public void updateTree()
	{
		for(int i = playerSkills.size()-1;i>0;i--){
			playerSkills.get(i).makeReady();
		}
	}
	/**
	 * 
	 * @return Returns a list of all unlocked skills in the tree
	 */
	public List<PlayerSkill> getUnlockedSkills()
	{
		List<PlayerSkill> unlocked = new ArrayList<PlayerSkill>();
		for(PlayerSkill skill:playerSkills)
		{
			if(skill.isUnlocked())
				unlocked.add(skill);
		}
		return unlocked;
	}

	public void add(PlayerSkill playerSkill) {
		playerSkills.add(playerSkill);
		
	}
	
	public PlayerSkill getSkill(int index)
	{
		return playerSkills.get(index);
	}
	
	public int getAmountOfSkills()
	{
		return playerSkills.size();
	}

	public void unlockSkill(PlayerSkill skill) {
		for(PlayerSkill treeSkill:playerSkills)
		{
			if(treeSkill.equals(skill))
				treeSkill.unlock();
		}
	}
	
}
