package game;

import java.awt.image.BufferedImage;

import runner.SkillTree;




public class PlayerSkill {
	private String skillName;
	private boolean isUnlocked, isAvail;
	private PlayerSkill reliesOn;
	private int spell, level=0;
	private final int maxLevel=5;
	private BufferedImage icon;
	
	
	/**
	 * Default Constructor
	 */
	public PlayerSkill()
	{
		skillName="Default Skill";
		isUnlocked = true;
		isAvail = false;
		reliesOn=null;
	}
	
	/**
	 * 
	 * @param name  Name of the Skill as shown on the tree
	 * @param skillTier Tier of the skill in the skill tree
	 * @param dependentOn The index of the skill in previous tier the must be unlocked first
	 */
	public PlayerSkill(String name, PlayerSkill dependentOn)
	{
		skillName=name;
		reliesOn=dependentOn;
		if(reliesOn==null)
		{
			isUnlocked=true;
			isAvail=false;
		}
		else
		{
			isUnlocked=false;
			isAvail=false;
		}
		spell=SkillTree.MAGIC_BALL;
		makeReady();
	}
	
	/**
	 * 
	 * @param name  Name of the Skill as shown on the skill tree
	 * @param skillTier Tier of the skill
	 * @param dependentOn The skill that needs to be unlocked before this skill
	 * @param effect  A single effect the the skill ahs on the player(Passive for now)
	 */
	public PlayerSkill(String name, PlayerSkill dependentOn, int spell)
	{
		skillName=name;
	
		reliesOn=dependentOn;
		if(reliesOn==null)
		{
			isUnlocked=true;
			isAvail=false;
		}
		else
		{
			isUnlocked=false;
			isAvail=false;
			makeReady();
		}
		this.spell=spell;
	}
	
	/**
	 * Method to unlock the skill for use
	 * @return  returns true if the skill was able to unlock, false otherwise
	 */
	public boolean unlock()
	{
		if(isAvail)
		{
			isUnlocked=true;
			isAvail=false;
			return true;
		}
		else
			if(level<maxLevel){
				level++;
				return true;
			}
			else
				return false;
			
	}
	
	/**
	 * Method to allow skill to be unlocked
	 * @return  returns true if the skill was able to be set to ready of unlocking, false otherwise
	 */
	public boolean makeReady()
	{
		if(reliesOn.isUnlocked()&& !isUnlocked())
		{
			isAvail=true;
			return true;
		}
		else
		{
			isAvail=false;
			return false;
		}
	}
	
	/**
	 * 
	 * @return returns true if the skill is unlocked, false otherwise
	 */
	public boolean isUnlocked()
	{
		return isUnlocked;
	}
	
	/** 
	 * 
	 * @return returns true if the skill is ready to be unlocked, false otherwise
	 */
	public boolean isAvail()
	{
		return isAvail;
	}
	
	public String getName()
	{
		return skillName;
	}
	

	public PlayerSkill getPrerequisiteSkill()
	{
		return reliesOn;
	}
	
	public int getLevel(){
		return level;
	}
	

	public int getSpell(){
		return spell;
	}

	public BufferedImage getIcon(){
		return icon;
	}
	
	/**
	 * @override
	 */
	public boolean equals(PlayerSkill skill){
		return skill.getName().equals(skillName);
	}
}
