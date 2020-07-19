package synhronizedModifier;

public class SynchronizedObjectLevel {

	public class DemoClass{
	    public synchronized void demoMethod(){}
	}
	
	// or ..
	
	public class DemoClass2{
	    public void demoMethod(){
	        synchronized (this)        {
	            //other thread safe code
	        }
	    }
	}
	
	// or..
	
	public class DemoClass3{
	    private final Object lock = new Object();
	    public void demoMethod(){
	        synchronized (lock)        {
	            //other thread safe code
	        }
	    }
	}
}
