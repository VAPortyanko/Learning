package arrayCloneAndCopy;

import java.util.Arrays;

public class SystemArray_Copy {
	
	public static void main(String[] args) {
		
		int[][] array1 = {{1, 2, 3}, {4, 5, 6}};
		int[][] array2 = new int[2][3];
		
		System.out.println("The System.arrayCopy is a shallow copy of the array!");
		
		System.arraycopy(array1, 0, array2, 0, array1.length);
		
		System.out.println(Arrays.deepToString(array1));
		System.out.println(Arrays.deepToString(array2));

	}
}

/* The System.arrayCopy is a shallow copy of the array. */