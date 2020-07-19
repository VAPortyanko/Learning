package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RRTRTRTRT {
	 public static boolean isValid(String braces) {
		if (braces.length()%2 !=0 ) return false;
				
		Map<Character, Character> bracesCharacters = new HashMap<>();
		bracesCharacters.put(']', '[');
		bracesCharacters.put('}', '{');
		bracesCharacters.put(')', '(');
		
		if (!bracesCharacters.containsValue(braces.charAt(0)))
			return false;
		if (!bracesCharacters.containsKey(braces.charAt(braces.length()-1)))
			return false;

		LinkedList<Character> stack = new LinkedList<>();
		
		for (int i = 0; i<braces.length(); i++) {
			Character element = braces.charAt(i);
			if (bracesCharacters.containsValue(element)) {
				stack.push(element);
			} else 
			if (bracesCharacters.get(element).equals(stack.element())) {
				stack.pop();
			} else 
			return false;	
		}
		
		return true;
		
	 }
	 
	 public static void main(String[] args) {
		System.out.println(isValid("(){([])}[]"));
	}
}		