package parametrizedTypes;

public class ParametrizedClass <T> {
	T ob;
	
	public ParametrizedClass(T ob) {
		this.ob = ob;
	}
	
	T getOb(){
		return ob;
	}
	
	void showType(){
		System.out.println("Type of the \"ob\" object: " + ob.getClass().getName());
	}
	
	public static void main(String[] args) {
		ParametrizedClass<String> obj1 = new ParametrizedClass<String>("String");
		obj1.showType();
		ParametrizedClass<Integer> obj2 = new ParametrizedClass<Integer>(new Integer(50));
		obj2.showType();
	}
}
