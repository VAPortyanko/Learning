package innerClasses.nestedClass;

public class LinkFromExternalOnNC {
	static public void main(String[] args){
		// Creating the NestedClassExample.Nested class outside the NestedClassExample class.  
		NestedClassExample.Nested inn = new NestedClassExample.Nested();
		// System.out.println(inn.c); variable "c" has private modifier. Therefore we can't get it outside the NestedClassExample.
		System.out.println(inn.d);
		inn.metod2();
		System.out.println(inn);
	}
}
