package throw_ex;

public class Throw {

	static void demo(){
		NullPointerException myExeption =  new NullPointerException("This excetion was created artificially.");
		throw myExeption;
	}
	
	public static void main(String[] args) {
		try{
			demo();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
