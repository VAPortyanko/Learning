package arraysAreCovariant;

import java.util.Arrays;

public class ArraysAreCovariant {
	
	public static void main(String[] args) {
		
		Object[] objectArray = new String[3];
		String[] stringArray = {"1", "2", "3"};
		
		System.out.println("Array of \"String\" elements:" + Arrays.toString(stringArray));
		System.out.println("Array of \"object\" elements:" + Arrays.toString(objectArray));
		
		System.out.println("\nobjectArray = stringArray;\n");
		objectArray = stringArray;
		
		System.out.println("Array of \"String\" elements:" + Arrays.toString(stringArray));
		System.out.println("Array of \"object\" elements:" + Arrays.toString(objectArray));
		
		System.out.print("\nobjectArray[0] = new Integer(10); //");
		try{
			objectArray[0] = new Integer(10); // java.lang.ArrayStoreException
		} catch (ArrayStoreException e) {
			System.out.println(e);
		}
	}
	
}
