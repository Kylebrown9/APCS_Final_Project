package spells;

import entitystuff.Entity;

public class DamageOverTime extends PassiveEffect {

	int damage, interval;
	
	public DamageOverTime(String name, Entity effected, int duration, int damage, int interval) {
		super(name,effected, duration);
		this.damage = damage;
		this.interval = interval;
	}

	public void doAction() {
		if(super.getCount() % interval == 0)
			super.effected.damage(damage);
	}
}
