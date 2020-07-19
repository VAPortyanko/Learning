package test;

interface CanHop{}
class BrazilianHornedFrog extends Frog{}
class TurtleFrog extends Frog{}

public class Frog implements CanHop{
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Object frog1 = new TurtleFrog();
		TurtleFrog frog2 = new TurtleFrog();
		Frog frog3 = new TurtleFrog();
		CanHop frog4 = new TurtleFrog();
	}
}