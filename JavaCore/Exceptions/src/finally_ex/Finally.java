package finally_ex;

public class Finally {
	public static void main(String[] args) {
		
		try{
			int a = 2+2;
			System.out.println("2 + 2 = " + a);
		}catch(NullPointerException e){
			System.out.println("Exeption was handled!");
		}finally{
			System.out.println("The finally block is always executes, even if it was not an exception");
		}
		
		try{
			int a = 2/(2-2);
			System.out.println(a);
		}finally{
			System.out.println("The finally block is always executed.");
		}
	}
}

/*The finally block don't execute when:
 1). System.exit(0);
 2). Another exception in block finally.
 3). Daemon thread death. 
*/