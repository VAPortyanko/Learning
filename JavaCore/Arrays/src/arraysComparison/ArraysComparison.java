// https://www.geeksforgeeks.org/compare-two-arrays-java/

package arraysComparison;

import java.util.Arrays;

public class ArraysComparison {
	public static void main(String[] args) {
		
		int[] array1 = new int[]{1, 2, 3, 4, 5};
		int[] array2 = new int[]{1, 2, 3, 4, 5};
		int[] array3 = new int[]{5, 4, 3, 2, 1};
		int[] array4 = new int[]{6, 7, 8, 9, 0};
		
		System.out.println("array1: " + Arrays.toString(array1));
		System.out.println("array2: " + Arrays.toString(array2));
		System.out.println("array3: " + Arrays.toString(array3));
		System.out.println("array4: " + Arrays.toString(array4));
		
		System.out.println("\narray1 == array2: " + (array1==array2));
		System.out.println("\narray1.equals(array2): " + array1.equals(array2));
		System.out.println("\nArrays.equals(array1, array2): " + Arrays.equals(array1, array2));
		System.out.println("Arrays.equals(array1, array3): " + Arrays.equals(array1, array3));
	}
}

// https://www.geeksforgeeks.org/compare-two-arrays-java/