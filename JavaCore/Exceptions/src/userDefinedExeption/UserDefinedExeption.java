package userDefinedExeption;

import java.util.Random;

public class UserDefinedExeption {
	public static void main(String[] args){
		
		Random rnd = new Random();
		int a = rnd.nextInt(20);
		System.out.println(a);
		
		try{
		if(a > 10)
			throw new MyNewExeption(a);
		}catch(MyNewExeption e){
			e.printStackTrace(System.out);
		}
	}
	
	static class MyNewExeption extends Exception{

		private static final long serialVersionUID = 1L;
		int detail;
		
		public MyNewExeption(int a) {
			detail = a;
		}
		public String toString(){
			return "Error, " + detail + " > 10";
		}
	}
}

