package modifier_static;

import java.util.ArrayList;
import java.util.List;

public class StaticBlock {
	
	static List<Character> alphabet;
	
	static {//static initialization(on first load class).
	    alphabet = new ArrayList<Character>();
	    for (char c='a'; c<='z'; c++) alphabet.add(c);
	}

	public static void main(String[] args) {
		System.out.println(alphabet);
	}
	
}
