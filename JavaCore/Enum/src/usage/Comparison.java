package usage;

public class Comparison {
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		Elements water = Elements.WATER;
		
		System.out.println(water == Elements.AIR);
		System.out.println(water == Elements.WATER);
		System.out.println(water == Elements.valueOf("WATER"));
		
		System.out.println(water.equals(Elements.AIR));
		System.out.println(water.equals(Elements.WATER));
		System.out.println(water.equals(Elements.valueOf("WATER")));
		
		//  but ... https://stackoverflow.com/questions/1750435/comparing-java-enum-members-or-equals
		Elements nothing = null;
		System.out.println("nothing == Elements.AIR : " + (nothing == Elements.AIR));
		System.out.println("nothing.equals(Elements.AIR) : " + nothing.equals(Elements.AIR));
		
	}
	
	enum Elements{
		WATER,
		FIRE,
		AIR;
	}
}