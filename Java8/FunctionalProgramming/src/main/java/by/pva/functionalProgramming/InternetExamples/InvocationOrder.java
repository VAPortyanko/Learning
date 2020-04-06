package by.pva.functionalProgramming.InternetExamples;

import java.time.LocalTime;
import java.util.stream.IntStream;

public class InvocationOrder {
	  public static void main (String[] args) {
	        IntStream stream = IntStream.of(8,4,7,1,3);
	        stream = stream.peek(i -> log("starting", i)).sorted().peek(i -> log("SORT:", i))
	                       .filter(i -> { log("filtering", i);
	                                      return i % 2 == 0;})
	                       .peek(i -> log("post filtering", i));
	        log("Invoking terminal method count.");
	        log("The count is", stream.count());
	        
	        System.out.println(); 
	        	        
	        IntStream stream2 = IntStream.of(8,4,7,1,3).parallel();
	        stream2 = stream2.peek(i -> log("starting", i)).sorted().peek(i -> log("SORT:", i))
	                       .filter(i -> {
	                           log("filtering", i);
	                           return i % 2 == 0;
	                       })
	                       .peek(i -> log("post filtering", i));
	        log("Invoking terminal method count.");
	        log("The count is", stream2.count());
	    }
	  
	    public static void log (Object... objects) {
	        String s = LocalTime.now().toString();
	        for (Object object : objects) {
	            s += " - " + object.toString();
	        }
	        System.out.println(s);
	        // putting a little delay so that we can see a clear difference
	        // with parallel stream.
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
}

// https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/lazy-evaluation.html

// If source is unordered ? - will the result be different (according sorted operation)? -it looks like yes -> check later.

