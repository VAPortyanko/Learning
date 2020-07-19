package polymorphism;

public class Example_02 {
	enum Direction { 
		   UP, DOWN; 
		 
		   public Direction opposite() { return this == UP ? DOWN : UP; } 
		} 
	
	// The same, but with polymorphism:
	enum Direction2 { 
		   UP { 
		        public Direction2 opposite() { return DOWN; } 
		   }, 
		   DOWN { 
		        public Direction2 opposite() { return UP; } 
		   }; 
		 
		   public abstract Direction2 opposite(); 
		} 
	
	public static void main(String[] args) {
		Direction d = Direction.UP;
		System.out.println(d);
		System.out.println(d.opposite());
		
		Direction2 d2 = Direction2.UP;
		System.out.println(d2);
		System.out.println(d2.opposite());
	}
}
//see http://www.quizful.net/post/java_enums