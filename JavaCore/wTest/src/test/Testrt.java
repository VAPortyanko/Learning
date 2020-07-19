package test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Testrt {
	public static void main(String[] args) {
		Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<>());
		set.add("aaa");
		set.add("bbb");
		        
		for (String s : set) {
		    set.add("ccc");
		    System.out.println(s);
		}
		
		for (String s : set) {
		    System.out.println(s);
		}
	}
}
