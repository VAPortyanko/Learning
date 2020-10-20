package parametrizedTypes;

public class ParametrizedConstructor_02 <T> {
	
	T obj;
	
	public <V> ParametrizedConstructor_02(V arg) {
		System.out.println(arg);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ParametrizedConstructor_02<String> pclass = new ParametrizedConstructor_02<String>(new Integer(6));
	}
}
