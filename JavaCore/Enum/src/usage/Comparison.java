package usage;

public class Comparison {
	public static void main(String[] args) {
		Elements water = Elements.WATER;
		
		System.out.println(water == Elements.AIR);
		System.out.println(water == Elements.WATER);
		System.out.println(water == Elements.valueOf("WATER"));
		
		System.out.println(water.equals(Elements.AIR));
		System.out.println(water.equals(Elements.WATER));
		System.out.println(water.equals(Elements.valueOf("WATER")));
		
		//  but ... https://stackoverflow.com/questions/1750435/comparing-java-enum-members-or-equals
	}
	
	enum Elements{
		WATER,
		FIRE,
		AIR;
	}
}