package collectionsClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CheckedCollection_method {
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String... args) {
    	
        Collection<String> c = new ArrayList<String>();
        Collections.addAll(c, "apple", "banana");
        System.out.println(c);

        Collection c2 = c;
        c2.add(1);
        System.out.println(c2);
// ************************************************************************        
        Collection<String> c3 = new ArrayList<>();
        c3 = Collections.checkedCollection(c3, String.class);
        Collections.addAll(c3, "apple", "banana");
        System.out.println(c3);

        Collection c4 = c3;
        c4.add(1);//this throws exception
        System.out.println(c4);
    }
}
