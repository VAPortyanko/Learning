// Returns the empty list (immutable). This list is serializable.
// This example illustrates the type-safe way to obtain an empty list:
//    List<String> s = Collections.emptyList();
// Implementation note: Implementations of this method need not create a separate List object for each call.
// Using this method is likely to have comparable cost
// to using the like-named field. (Unlike this method, the field does not provide type safety.)
package collectionsClass;

import java.util.Collection;
import java.util.Collections;

public class EmptyList_method {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Collection<String> collect1 = Collections.emptyList();
	}
}
