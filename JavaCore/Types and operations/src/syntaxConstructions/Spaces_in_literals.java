package syntaxConstructions;

import java.io.IOException;

public class Spaces_in_literals {
	public static void main(String[] args) throws IOException {

	    int x = 100_000_000;
	    System.out.println(x);

	    int y = 0b1010;        
	    System.out.println(y);
	}
}
