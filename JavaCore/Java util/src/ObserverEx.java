import java.util.Observable;
import java.util.Observer;

class Watcher implements Observer{
	@Override
	public void update(Observable o, Object arg) {
		if ((int) arg <100){
			System.out.println("Attention! Selling currency below the established rate!");
			((BeingWatched) o).PosaditVTurmu();
		}
	}
}

class BeingWatched extends Observable{
	void counter(int count){
		for(; count>=0; count--){
			int b = sell();
			setChanged();
			notifyObservers(new Integer(b));
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				count=-1;
			}
		}
	}
	
	int sell(){
		int a =(int) (Math.random()*530);
		System.out.println("Dollar sold at the rate - " + a);
		return a;
	}
	
	void PosaditVTurmu(){
		System.out.println("Currency speculator is sent to a prison!");
		Thread.currentThread().interrupt();
	}
}


public class ObserverEx {
	public static void main(String[] args) {
		BeingWatched observered = new BeingWatched();
		Watcher abservering = new Watcher();
		observered.addObserver(abservering);
		observered.counter(100);
	}
}
