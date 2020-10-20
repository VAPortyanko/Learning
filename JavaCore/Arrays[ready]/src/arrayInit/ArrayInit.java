package arrayInit;

public class ArrayInit {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		int[] arr1   = new int[10];
		int[][] arr2 = new int[2][2];
		int[] arr3[] = new int[3][3];
		
		int arr4[] = {1,2,3,4,5};
		
		int arr5[][] = new int[2][];
		arr5[0] = new int[]{1, 3, 3, 4};
		arr5[1] = new int[2];
		
		int[][] arr6 = {{1,2,3,4,5},
				        {6,7,8,9,0}};
		
		int[][] arr7 = {new int[]{1, 2, 3}, 
				        new int[]{4, 5, 6}, 
				        new int[]{7, 8, 9}};
	}
}