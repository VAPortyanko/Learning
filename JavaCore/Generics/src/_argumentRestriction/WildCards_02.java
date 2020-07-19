package _argumentRestriction;

public class WildCards_02 <T extends Number>{
	T[] massive;
	
	public WildCards_02(T[] mas) {
		massive = mas;
	}
	
	T massiveMaxValue(){
		T max = massive[1];
		for(int i = 1; i<massive.length; i++){
			if (massive[i].doubleValue() > max.doubleValue()){
				max = massive[i];
			}
		}
		return max;
	}
	
	// "?" - it is unknown type, otherwise we could not compare objects parameterized by different types.
	boolean aMoreThenB(WildCards_02<?> ob){
		if(massiveMaxValue().doubleValue() < ob.massiveMaxValue().doubleValue()){
			return true;
		}else 
			return false;
	}
	
	
	public static void main(String[] args) {
		Integer[] mas1 = {1, 5, 7, 3}; 		
		Double[] mas2 = {1.2, 4.5, 6.7};
		
		WildCards_02<Integer> ob1 = new WildCards_02<Integer>(mas1);
		WildCards_02<Double> ob2 = new WildCards_02<Double>(mas2);
		System.out.println("Max value in the first array: " + ob1.massiveMaxValue());
		System.out.println("Max value in the second array: " + ob2.massiveMaxValue());
		System.out.println("Is the maximum value in the second array more than the maximum value in the first one?: " + ob1.aMoreThenB(ob2));
	}
}