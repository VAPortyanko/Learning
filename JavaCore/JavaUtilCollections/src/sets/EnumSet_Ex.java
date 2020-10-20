package sets;

import java.util.*;

enum days {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

public class EnumSet_Ex {
	public static void main(String[] args) {

		Set<days> set = EnumSet.of(days.TUESDAY, days.WEDNESDAY);

		// Traversing elements
		Iterator<days> iter = set.iterator();
		while (iter.hasNext())
			System.out.print("[" + iter.next() + "]");

		Set<days> set1 = EnumSet.allOf(days.class);
		System.out.println("\nWeek Days:" + set1);
		
		Set<days> set2 = EnumSet.noneOf(days.class);
		System.out.println("Week Days:" + set2);
	}
}