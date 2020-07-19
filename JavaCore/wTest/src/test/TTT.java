package test;

public class TTT extends TT{
	TTT(){
		System.out.println("TTT");
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TTT t = new TTT();
	}
}

class TT{
	TT(){
		System.out.println("TT");
	}
}
