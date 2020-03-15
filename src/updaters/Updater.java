package updaters;

import java.util.List;
import java.util.ListIterator;

public class Updater extends Thread {
	
	List<Updatable> updatables;
	ListIterator<Updatable> updateIT;
	boolean running;
	
	public Updater(List<Updatable> updatables) {
		this.updatables = updatables;
	}
	
	public void run() {
		running = true;
		
		while(running) {
			updateAll();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {}
		}
		
		updateAll();
	}
	
	public void end() {
		running = false;
	}
	
	public void updateAll() {
		updateIT = updatables.listIterator();
		
		while(updateIT.hasNext())
			updateIT.next().update();
	}
}
