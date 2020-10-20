package syntaxConstructions;

public class TenerniiOperator {
	public static void main(String[] args) {
		int x = 10;
		int y =20;
		int z;
		// if true (x<y) then (x) else (y)
		z = x<y ? x : y;
		System.out.println(z);
	}
}
