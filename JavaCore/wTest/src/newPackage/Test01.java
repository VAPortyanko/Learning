package newPackage;

public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Colors myColor = Colors.BLUE;

	}
	
}

enum Colors{
	RED, GREEN, BLUE;
	
	Colors() {
		System.out.println("Color:" + this);
	}
}