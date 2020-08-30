package collectionInterafece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class Method_RemoveAll {
	public static void main(String[] args) {
		
		Collection<String> col1 = new ArrayList<String>(Arrays.asList("1", null, "2", null, "4", null));
		Collection<String> col2 = col1.stream().collect(Collectors.toCollection(ArrayList::new));

		col1.remove(null);
		System.out.println(col1);
		col2.removeAll(Collections.singleton(null));
		System.out.println(col2);
		
	}	
}
