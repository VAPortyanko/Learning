package multiParameter;

public class MultiParameterClass<T, V> {
	
	T obT;
	V obV;
	
	public MultiParameterClass(T ob1, V ob2) {
		obT = ob1;
		obV = ob2;
	}
	
	T getObT(){
		return obT;
	}
	
	V getObV(){
		return obV;
	}
	
	void showAll(){
		System.out.println("Type of the first parameter: " + obT.getClass().getName());
		System.out.println("Type of the second parameter: " + obV.getClass().getName());
	}
	
	public static void main(String[] args) {
		MultiParameterClass<String, Integer> ob = new MultiParameterClass<String, Integer>("Hello", 10);
		ob.showAll();
	}
}
