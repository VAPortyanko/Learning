package maps.hashMap;

import java.util.HashMap;

public class HashMapAfterChangeElementHashCode {
	public static void main(String[] args) {
		HashMap<SomeClass, String> hs = new HashMap<>();
		SomeClass element1 = new SomeClass(1);
		
		hs.put(element1, "1");
		System.out.println("Map after add element1: \n" + hs);
		
		element1.setI(2);  // Change the element1 state (it will be caused of changing hashcode this object);
		System.out.println("After changing hashcode try to find element: \n" + hs.get(element1)); // try to find the object in the map.
		
		System.out.println("Map after changing hashcode of the element1: \n" + hs);
		System.out.println("This element can't be find by the key.");
	}
	
	static class SomeClass{
		private int i;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SomeClass other = (SomeClass) obj;
			if (i != other.i)
				return false;
			return true;
		}

		public SomeClass(int i) {
			super();
			this.i = i;
		}

		@Override
		public String toString() {
			return "[i=" + i + "]";
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
		
		
	}
}
