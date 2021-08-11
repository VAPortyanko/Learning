package javaUtillArrays;

import java.util.Arrays;

public class Java_Util_Arrays {
	
	public static void main(String[] args) {
		
		int[] array1 = new int[]{1, 2, 4, 5, 6, 7, 8};
		
		// Array must be sorted. If the array contains multiple elements with the specified value, there is no guarantee which one will be found.
		int searchResult = Arrays.binarySearch(array1, 5);   
		System.out.println(searchResult);
	
		// Other methods ...
		System.out.println(Arrays.deepToString(new Integer[][]{}));
		
	}
	
}
