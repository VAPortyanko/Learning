package concurentCollections.sclableMaps;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapDemo {

	private final ConcurrentHashMap<Integer, String> conHashMap = new ConcurrentHashMap<Integer, String>();

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		ConcurrentHashMapDemo ob = new ConcurrentHashMapDemo();
		
		service.execute(ob.new WriteThreasOne());
		service.execute(ob.new WriteThreasTwo());
		service.execute(ob.new ReadThread());
		service.shutdownNow();
	}

	class WriteThreasOne implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i <= 10; i++) {
				conHashMap.putIfAbsent(i, "A" + i);
			}
		}
	}

	class WriteThreasTwo implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i <= 5; i++) {
				conHashMap.put(i, "B" + i);
			}
		}
	}

	class ReadThread implements Runnable {
		@Override
		public void run() {
			Iterator<Integer> ite = conHashMap.keySet().iterator();
			while (ite.hasNext()) {
				Integer key = ite.next();
				System.out.println(key + " : " + conHashMap.get(key));
			}
		}
	}
}

// https://www.concretepage.com/java/example_concurrenthashmap_java
// https://www.baeldung.com/java-concurrent-map - addition read.
