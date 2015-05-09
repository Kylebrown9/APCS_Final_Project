package updaters;

import java.util.List;
import java.util.ListIterator;

public class Updater extends Thread {
	
	List<Updatable> updatables;
	boolean running;
	
	public Updater(List<Updatable> updatables) {
		this.updatables = updatables;
	}
	
	public void run() {
		ListIterator<Updatable> updateIT;
		boolean sentinel = false;
		running = true;
		
		while(running) {
			updateIT = updatables.listIterator();
			
			while(updateIT.hasNext()) {
				updateIT.next().update();
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					sentinel = true;
				}
				
				if(sentinel)
					System.out.println("Overran thread kill");
			}
		}
	}
	
	public void end() {
		running = false;
	}
}
