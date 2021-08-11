package throws_ex;

public class Throws {
	
	static void say() throws ClassNotFoundException{
		throw new ClassNotFoundException(); /* Checked exception must be processed with try-catch-finally block or specified in class declaration */
	}
	
	static void say2(){
		throw new NullPointerException();  /* Unchecked exception doesn't need processing */
	}
	
	public static void main(String[] args){
		
		try{
			say();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		try {
			say2();
		} catch (NullPointerException e) {
			e.printStackTrace(); 
		}
	}
}
/* The overridden method can't extend the list of possible exceptions to the original method.  
 - no new exceptionsType.
 - no exceptionType higher than the exception of the parent class. 
 https://stackoverflow.com/questions/5875414/why-cant-overriding-methods-throw-exceptions-broader-than-the-overridden-method
 */