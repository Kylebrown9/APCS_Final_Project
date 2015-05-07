package entitystuff;

import game.Map;

public class Player extends GameCharacter {

	public Player(Map m, int x, int y) {
		super(m, "/Resources/player.png",x,y);
	}
}
