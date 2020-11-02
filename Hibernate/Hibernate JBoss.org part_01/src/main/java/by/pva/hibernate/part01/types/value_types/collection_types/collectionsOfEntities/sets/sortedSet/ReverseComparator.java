package by.pva.hibernate.part01.types.value_types.collection_types.collectionsOfEntities.sets.sortedSet;

import java.util.Comparator;

public class ReverseComparator implements Comparator<Phone19> {

	@Override
	public int compare(Phone19 o1, Phone19 o2) {
		return o2.compareTo( o1 );
	}
}
