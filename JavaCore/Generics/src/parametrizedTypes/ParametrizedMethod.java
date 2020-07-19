package parametrizedTypes;

public class ParametrizedMethod {

	// The method "say" displays a double value of the input parameter of the Number class or its inheritors.
	<T extends Number>void say(T t){
		System.out.println(t.doubleValue());
	}
	
	public static void main(String[] args) {
		
		ParametrizedMethod cls = new ParametrizedMethod();
		
		Integer num1 = new Integer(10);
		Double num2 = new Double(90.7);
		Byte num3 = new Byte((byte) 10); 
		Long num4 = new Long(24L); 

		cls.say(num1); // cls.<Integer>say(num1);
		cls.say(num2);
		cls.say(num3);
		cls.say(num4);
	}	
}
