package overloadingAndOverriding;

public class Covarience {

	public static void main(String[] args) {
		
		Shape s = new ShapeBuilder().build();
		System.out.println(s);
		s = new CircleBuilder().build();
		System.out.println(s);
		
	}
}

class Shape{
	public String toString(){return "shape";}
}

class Circle extends Shape{
	public String toString(){return "circle";}
}

class ShapeBuilder{
	Shape build(){return new Shape();}
}

class CircleBuilder extends ShapeBuilder{
	@Override
	Circle build(){return new Circle();} // Raising the type of the returned result. 
}