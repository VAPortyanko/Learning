package arithmetic;

public class IntDevideIntToFloat {
	public static void main(String[] args) {
		int a = 3;
		int b = 2;
		float c = a/b;
		float d = (float)a/b;
		System.out.println("float = int/int (3/2=" + c + ")");
		System.out.println("float = (float)int/int (3/2=" + d + ")");
	}
}
