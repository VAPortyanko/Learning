package wrapperTypes.character;

public abstract class CharacterEx {
	public static void main(String[] args) {
		Character c1 = new Character('1'); 
		Character c2 = new Character('a');
		Character c3 = new Character(' ');
		Character c4 = new Character('g');
		Character c5 = new Character('G');
		
		System.out.println(Character.isDigit(c1));
		System.out.println(Character.isLetter(c2));
		System.out.println(Character.isWhitespace(c3));
		System.out.println(Character.isLowerCase(c4));
		System.out.println(Character.isUpperCase(c5));
		
		System.out.println("---------------------------------------------");
		
		System.out.println((int)Character.MAX_VALUE);
		System.out.println((int)Character.MIN_VALUE);
		
	}
}