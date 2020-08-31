package parametrizedTypes;

public class ParametrizedConstructor_01 {
	
	public <T> ParametrizedConstructor_01(T arg) {
		System.out.println(arg.getClass().getName());
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		ParametrizedConstructor_01 clazz = new ParametrizedConstructor_01("Hello!");
		ParametrizedConstructor_01 clazz2 = new ParametrizedConstructor_01(new Integer(10));
		
	}
}
