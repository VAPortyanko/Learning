package synhronizedModifier;

public class SynchronizedClassLevel {

	public static class DemoClass{                     
	    public synchronized static void demoMethod(){}
	}
	
	// or ..
	
	public class DemoClass2{
	    public void demoMethod(){
	        synchronized (DemoClass2.class)        {
	            //other thread safe code
	        }
	    }
	}
}
