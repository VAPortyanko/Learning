package _parameterRestriction;

// The generic class is limited by the Point type and its inheritors. Implements PointInterface interface.
public class LimitedParameters<T extends Point&PointInterface> {
	
	 T o;
	
	public LimitedParameters(T o) {
		this.o = o;
	}

	public static void main(String[] args) {
		LimitedParameters<Point> ob1 = new LimitedParameters<Point>(new Point(10, 20));
		LimitedParameters<ColorPoint> ob2 = new LimitedParameters<ColorPoint>(new ColorPoint(15, 35, "RED"));;
		//ClassWithRestrictionType<String> ob3; Error! Only Point class or its inheritors can be a parameter of the class ClassWithRestrictionType.
		
		ob1.o.showCoordinates();
		ob2.o.showCoordinates();
	}
	
	// Only methods and fields of the Point (the limiting class) class is accessible for the object "o".
	// If you remove from the declaration of the parameterized type "extends Point", 
	// then only the methods and fields of the Object class will be available (since in this case it will be the limiting class)
	void FreeMetods(){
		o.showCoordinates();
	}
}

class Point implements PointInterface{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void showCoordinates(){
		System.out.println("x = " + x + " y = " + y);
	}
}

class ColorPoint extends Point{
	String color;
	
	public ColorPoint(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}
	
	public void showCoordinates(){
		System.out.println("x = " + x + " y = " + y + " color = " + color);
	}
}

interface PointInterface{
	void showCoordinates();
}

// Class A { /* ... */ }
// interface B { /* ... */ }
// interface C { /* ... */ }
// class D <T extends A & B & C> { /* ... */ }
// class D <T extends B & A & C> { /* ... */ }  // compile-time error