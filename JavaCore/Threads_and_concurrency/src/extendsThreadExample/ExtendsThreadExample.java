package extendsThreadExample;

public class ExtendsThreadExample {
	public static void main(String[] args) {
		new NewThred();
		
		try{
			for (int i=10; i>0; i--){
				System.out.println("Главный поток:" + i);
				Thread.sleep(300);
			}
		}catch(InterruptedException e){
			System.out.println("Главный поток прерван.");
		}
		System.out.println("Главный поток завершен.");
	}
	
	static class NewThred extends Thread{
		public NewThred() {
			super("Демо-поток.");
			System.out.println("Дочерний поток" + this);
			start();
		}
		
		public void run(){
			try{
				for (int i=10; i>0; i--){
					System.out.println("Дочерний поток:" + i);
					Thread.sleep(500);
				}
			}catch(InterruptedException e){
				System.out.println("Дочерний поток прерван.");
			}
			System.out.println("Дочерний поток завершен.");
		}
	}
	
}