package extendsThreadExample;

public class ExtendsThreadExample {
	public static void main(String[] args) {
		new NewThred();
		
		try{
			for (int i=10; i>0; i--){
				System.out.println("Main thread:" + i);
				Thread.sleep(300);
			}
		}catch(InterruptedException e){
			System.out.println("Main thread is interrupted.");
		}
		System.out.println("Main thread is finished.");
	}
	
	static class NewThred extends Thread{
		public NewThred() {
			super("Demo-thread");
			System.out.println("Child thread:" + this);
			start();
		}
		
		public void run(){
			try{
				for (int i=10; i>0; i--){
					System.out.println("Child thread:" + i);
					Thread.sleep(500);
				}
			}catch(InterruptedException e){
				System.out.println("Child thread is interrupted.");
			}
			System.out.println("Child thread is finished.");
		}
	}
	
}