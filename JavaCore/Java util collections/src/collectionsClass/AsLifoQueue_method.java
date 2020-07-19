/* 
 * The Collections.asLifoQueue() method returns a view of a Deque
 * as a Last-in-first-out (Lifo) Queue. Method add is mapped to push, 
 * remove is mapped to pop and so on. This view can be useful
 * when you would like to use a method requiring a Queue but you need Lifo ordering.
 * https://www.java2novice.com/java-collections-and-util/collections/aslifoqueue/
 * 
 * The Collections.asLifeQueue method doesn't affect elements already in the original collection.
 * https://stackoverflow.com/questions/42750309/why-java-collections-aslifoqueue-is-not-lifo
 */
package collectionsClass;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

public class AsLifoQueue_method {

	public static void main(String args[]) {

		Deque<String> dq = new ArrayDeque<String>(5);
		dq.add("java");
		dq.add("c");
		dq.add("c++");
		dq.add("unix");
		dq.add("perl");
		Queue<String> q = Collections.asLifoQueue(dq);
		System.out.println("The returned queue is: " + q);
	}
}
