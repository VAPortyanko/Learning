package iterable;

import java.util.ArrayList;

public class ForEachLoop {

	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		for(Integer i:list){          // Type of "i" can be: <? super T> (Integer or Number or Object).
			System.out.println(i);
		}
	}
}
