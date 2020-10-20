package usage;

public class Iteration {
	public static void main(String[] args) {

        for (Colors color : Colors.values()) {
            System.out.println(color);
        }
	}
	
	enum Colors {
		RED,
		ORANGE,
		YELLOW,
		GREEN,
		CYAN,
		BLUE,
		VIOLET;
	}
}
