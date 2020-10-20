package syntaxConstructions;

public class OrAndOrOperator {
	public static void main(String[] args) {
		int a = 0;
		int b = 10;
		if(a == 0 || b/0 >8){
			System.out.println("Operator b/0>8 is not checking because first expresion = true");
		}
	}
}
