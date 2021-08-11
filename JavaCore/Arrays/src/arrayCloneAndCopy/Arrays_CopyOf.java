package arrayCloneAndCopy;

import java.util.Arrays;

public class Arrays_CopyOf {
	
	public static void main(String[] args) {
		
		int[][] array1 = {{1, 2, 3}, {4, 5, 6}};
		int[][] array2 = new int[2][3];
		
		System.out.println(" The Array.copyOf is a shallow copy of the array!");
		
		array2 = Arrays.copyOf(array1, array1.length);
		
		System.out.println(Arrays.deepToString(array1));
		System.out.println(Arrays.deepToString(array2));
		
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		
		System.out.println(array1);
		System.out.println(array2);
	}
	
}

/* The Array.copyOf() is a shallow copy of the array. */