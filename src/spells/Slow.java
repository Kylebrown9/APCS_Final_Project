package spells;

import entitystuff.Entity;

public class Slow extends PassiveEffect {

	private double slow;
	
	public Slow(String name, Entity effected, int duration, double slow) {
		super(name, effected, duration);
		this.slow = slow;
	}
	
	public void doAction() {
		effected.speed *= slow;
	}
}
