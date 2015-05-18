package game;

public class Attribute {

	String effectType;
	float effectAmount;
	int timeRemaining;
	
	public Attribute()
	{
		effectType="health";
		effectAmount=(float) 0.0;
		timeRemaining=-1;
	}
	
	public Attribute(String type, float amt)
	{
		effectType=type;
		effectAmount=amt;
		timeRemaining=-1;
	}
	
	public Attribute(String type, double amt){
		effectType=type;
		effectAmount=(float)amt;
	}

	
	/**
	 * @return Return a string representation of the stat to effect
	 */
	public String getEffectType() {
		return effectType;
	}

	
	/**
	 * @return The decimal amount to change the effect's stat (ex. 10% == .1)
	 */
	public float getEffectAmount() {
		return effectAmount;
	}
	
	
	/**
	 * 
	 * @return Returns the time (unit not established) left until the effect wears off
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}

}