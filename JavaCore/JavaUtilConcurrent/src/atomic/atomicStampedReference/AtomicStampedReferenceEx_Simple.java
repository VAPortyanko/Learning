// http://tutorials.jenkov.com/java-util-concurrent/atomicstampedreference.html
package atomic.atomicStampedReference;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceEx_Simple implements Runnable{

	private final AtomicStampedReference<String> atomicStringReference;
	
	public AtomicStampedReferenceEx_Simple(AtomicStampedReference<String> atomicStringReference) {
		this.atomicStringReference = atomicStringReference;
	}

	public void run() {
		int[] stampHolder = new int[1];
		String initialRef = atomicStringReference.get(stampHolder); // Атомарно получаем значение ссылки и штампа.
		int initialStamp = stampHolder[0];

		String newRef   = "new value referenced";
		int newStamp = initialStamp + 1;

		boolean exchanged = atomicStringReference.compareAndSet(initialRef, newRef, initialStamp, newStamp);
		System.out.println("exchanged: " + exchanged);  

		exchanged = atomicStringReference.compareAndSet(initialRef, "new string", newStamp, newStamp + 1);
		System.out.println("exchanged: " + exchanged);  

		exchanged = atomicStringReference.compareAndSet(newRef, "new string", initialStamp, newStamp + 1);
		System.out.println("exchanged: " + exchanged); 

		exchanged = atomicStringReference.compareAndSet(newRef, "new string", newStamp, newStamp + 1);
		System.out.println("exchanged: " + exchanged);
	}
	
	public static void main(String[] args) {
		String initialRef   = "initial value referenced";
		int    initialStamp = 0;
		AtomicStampedReference<String> atomicStringReference = new AtomicStampedReference<String>(initialRef, initialStamp);
		
		new Thread(new AtomicStampedReferenceEx_Simple(atomicStringReference)).start();
	}
}
