// Primitive lock implementation
// http://tutorials.jenkov.com/java-concurrency/locks.html
package lockInterface;

public class SimpleLockImplementation {
	
	  private boolean isLocked = false;

	  public synchronized void lock() throws InterruptedException{
	    while(isLocked){
	      wait();
	    }
	    isLocked = true;
	  }

	  public synchronized void unlock(){
	    isLocked = false;
	    notifyAll();
	  }
}
