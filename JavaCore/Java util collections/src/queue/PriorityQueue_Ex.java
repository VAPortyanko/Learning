// https://javadevblog.com/primer-ispol-zovaniya-java-priority-queue-priorityqueue.html
package queue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueue_Ex{
    public static void main(String[] args){
    	
        Comparator<String> comparator = new StringLengthComparator();
        PriorityQueue<String> queue = new PriorityQueue<String>(10, comparator);
        
        queue.add("short");
        queue.add("very long indeed");
        queue.add("medium");
        
        System.out.println(queue);                 // order according insert.
        
        Iterator<String> it = queue.iterator();    // order according insert.
        while (it.hasNext()){
        	System.out.print(it.next());
        	if (it.hasNext())
        		System.out.print(", ");
        	else
        		System.out.println();
        }
        	
        while (queue.size() != 0) {
            System.out.println(queue.remove());    // order according comparator.
        }
    }
}

class StringLengthComparator implements Comparator<String>
{
    @Override
    public int compare(String x, String y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.length() < y.length()){
            return -1;
        }
        if (x.length() > y.length()){
            return 1;
        }
        return 0;
    }
}