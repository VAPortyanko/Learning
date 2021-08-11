package arrayCloneAndCopy;

import java.util.Arrays;

public class Clone {
	public static void main(String[] args) {
		
		char[] array1 = {'q', 'w', 'e', 'r', 't', 'y'};
		char[] array2 = new char[6];
		char[] array3 = new char[6];
		
		System.out.println("For 1D array with elements of the primitive type method \"clone\" works!");
		System.out.println(array1.toString() + " -  " + Arrays.toString(array1));
		System.out.println(array2.toString() + " -  " + Arrays.toString(array2));
		
		array3 = array1.clone();
		System.out.println(array3.toString() + " -  " + Arrays.toString(array3));
		
		
		
		int[][] array4 = {{1,2,3,4,5},{6,7,8,9,0}};
		int[][] array5 = array4.clone();
		
		System.out.println();
		System.out.println("For 2D array with elements of the primitive type method \"clone\" doesn't work! It is shallow copy(elements don't copy)!");
		System.out.println(array4.toString());
		System.out.println(array5.toString());
		System.out.println(array4[0].toString());
		System.out.println(array5[0].toString());
		
		System.out.println();
		System.out.println("Clone with reference type elements doesn't work too!");
	}
}
/* The clone is a shallow copy of the array. */