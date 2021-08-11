package innerClasses.AnonymousClass;

public class AnonimClassExample {
	
	static public void main(String[] args){
		
		SomeClass annonimClass = new SomeClass(){
			
			int field = 2020;
			
			@Override
			public String toString(){
				return "Anonim class! " + field + " " + super.field + "!";
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
