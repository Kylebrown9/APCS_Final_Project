package updaters;

import java.util.ArrayList;
import java.util.List;

public class PausableUpdater {
	Runnable update;
	Updater updater;
	List<Updatable> updatables = new ArrayList<Updatable>();
	boolean playing;
	
	public PausableUpdater(Updatable u) {
		updatables.add(u);
		
		playing = false;
	}
	
	public PausableUpdater(Updatable u1, Updatable u2) {
		updatables.add(u1);
		updatables.add(u2);
		
		playing = false;
	}
	
	public PausableUpdater(List<Updatable> updatables) {
		this.updatables = updatables;
	
		playing = false;
	}
	
	/**
	 * constructs and starts a new Updater updating updatables
	 */
	public void play() {
		if(playing)
			return;
		
		updater = new Updater(updatables);
		updater.start();
		playing = true;
	}
	
	/**
	 * interrupts the current Updater
	 */
	public void pause() {
		if(!playing)
			return;
		
		updater.end();
		playing = false;
	}
	
	public void flip() {
		if(playing) 
			pause();
		else
			play();
		
		//System.out.println("The game is playing " + playing);
	}
}
