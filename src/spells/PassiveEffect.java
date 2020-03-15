package spells;

import entitystuff.Entity;

public abstract class PassiveEffect {
	private int count=0, length;
	Entity effected;
	String name;
	
	public PassiveEffect(String name, Entity effected, int duration) {
		this.effected = effected;
		length = duration;
		this.name = name;
	}
	
	public void update() {
		count++;
		doAction();
	}
	
	public boolean isDone() 	{return count > length;}
	public int getCount() 		{return count;}
	public String toString() 	{return name;}
	
	public abstract void doAction();
}
