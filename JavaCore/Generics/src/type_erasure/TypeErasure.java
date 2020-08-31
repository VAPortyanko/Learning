// !!! https://www.baeldung.com/java-type-erasure !!!


package type_erasure;
//import java.util.Arrays;

public class TypeErasure<E> {
	
	
//	public static void main(String[] args) {
//		GenericArray<String> array = new GenericArray<>();
//		array.displayArray();
//		array.add("FFF", 0);
//		array.add("DDD", 1);
//		array.displayArray();
//		System.out.println(array.get(1));
//		
//	}
}

//class GenericArray<T>{
//	
//	Object[] array = new Object[10];
//	
//	void displayArray() {
//		System.out.println("Array: " + Arrays.toString(array));
//	}
//	
//	void add(T element, int pos) {
//		array[pos] = element;
//	}
//	
//	T get(int pos) {
//		return (T)array[pos];
//	}
//	
//}