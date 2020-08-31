package _argumentRestriction;

public class WildCards_01<T extends TwoD> {
	
	T[] ob;
	
	public WildCards_01(T[] ob) {
		this.ob = ob;
	}
	
	static void showXY(WildCards_01<? extends TwoD> t1){
		for (int i = 0; i < t1.ob.length; i++)
			t1.ob[i].showXY();
	}
	
	static void showXYZ(WildCards_01<? extends ThreeD> t1){
		for (int i = 0; i < t1.ob.length; i++)
			t1.ob[i].showXYZ();
	}
	
	static void showXYZT(WildCards_01<? extends FourD> t1){
		for (int i = 0; i < t1.ob.length; i++)
			t1.ob[i].showXYZT();
	}
	
	public static void main(String[] args) {
		
		TwoD twoD[] = {new TwoD(2, 3), new TwoD(1, 1), new TwoD(4, 7)};
		ThreeD threeD[] = {new ThreeD(2, 3, 4), new ThreeD(1, 1, 6), new ThreeD(4, 5, 6)};
		FourD fourD[] = {new FourD(2, 3, 8 ,6), new FourD(1, 1, 1, 1), new FourD(4, 7, 1, 5)};
		
		WildCards_01<? extends TwoD> ob1 = new WildCards_01<>(twoD);
		WildCards_01<? extends ThreeD> ob2 = new WildCards_01<>(threeD);
		WildCards_01<? extends FourD> ob3 = new WildCards_01<>(fourD);
		
		
		showXY(ob1); // This method can accept ob1, ob2, ob3 as a parameter.
		System.out.println("------------------------------------------------");
		showXYZ(ob2);// This method can accept ob2, ob3 as a parameter.
		System.out.println("------------------------------------------------");
		showXYZT(ob3);// This method can accept only ob3 as a parameter.

	}
}




class TwoD{
	int x, y;
	
	TwoD(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void showXY(){
		System.out.println("x = " + x + " y = " + y);
	}
}

class ThreeD extends TwoD{
	int z;
	
	public ThreeD(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
	void showXYZ(){
		System.out.println("x = " + x + " y = " + y + " z = " + z);
	}
}

class FourD extends ThreeD{
	int t;
	
	public FourD(int x, int y, int z, int t) {
		super(x, y, z);
		this.t = t;
	}
	
	void showXYZT(){
		System.out.println("x = " + x + " y = " + y + " z = " + z + " t = " + t);
	}
}
