package collectionsClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

class LazyObjectInitialization {

	   private Collection<String> items; 

	   public final Iterator<String> items() {

	      if(items == null || items.isEmpty()) {
	        return Collections.emptyIterator(); 
	      }

	      return items.iterator();
	   }

	   public final void add(String item) { 

	      if(items == null) {
	        items = new ArrayList<>();
	      }

	      items.add(item);
	   }
}