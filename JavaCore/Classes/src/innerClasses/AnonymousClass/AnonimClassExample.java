package innerClasses.AnonymousClass;

public class AnonimClassExample {
	
	static public void main(String[] args){
		
		SomeClass annonimClass = new SomeClass(){
			int a = 2020;
			@Override
			public String toString(){
				return "Hello World! " + a + " " + super.field + "!";
			}
		};
		
		System.out.println(annonimClass);
		
	} 
}

class SomeClass{
	String field = "year";
	@Override
	public String toString() {
		return "MyClass[field=" + field + "]";
	}
}
