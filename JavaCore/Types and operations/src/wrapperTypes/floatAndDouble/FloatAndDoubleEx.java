package wrapperTypes.floatAndDouble;

public class FloatAndDoubleEx {
	public static void main(String[] args) {
		Double d1 = new Double(10.5);
		@SuppressWarnings("unused")
		Double d2 = new Double("134.7");
		
		@SuppressWarnings("unused")
		Float f1 = new Float(10.6f); 
		//Float f2 = new Float("10.67a");
		@SuppressWarnings("unused")
		Float f3 = new Float(d1);
		
		System.out.println(Double.SIZE);
		System.out.println(Float.SIZE);
		
		Double d3 = 1/0.;
		Double d4 = -1/0.;
		Double d5 = 0/0.; 
		
		System.out.println(d3);
		System.out.println(d4);
		System.out.println(d5);
		
		System.out.println(Double.isInfinite(d3));
		System.out.println(Double.isInfinite(d4));
		System.out.println(Double.isNaN(d5));
	}
}
