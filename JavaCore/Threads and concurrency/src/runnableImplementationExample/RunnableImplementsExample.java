package runnableImplementationExample;

class RunnableImplementsExample{
	
	public static void main(String[] args) {
		new RunnableImplementsThread();
		
		try {
			for(int i = 30; i>0; i--){
				System.out.println("Main thread: " + i);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("The main thread is interrupted.");
		}
		System.out.println("The main thread is finished.");
	}
	
	
	static class RunnableImplementsThread implements Runnable{

		Thread t; 
		
		RunnableImplementsThread(){
			t = new Thread(this, "DemoThread");
			System.out.println("A child thread is created " + t);
			t.start();
		}
		
		@Override
		public void run() {
			try{
				for(int i = 30; i>0; i--){
					System.out.println("Child thread:" + i);
					Thread.sleep(100);
				}
				}catch (InterruptedException e) {
					System.out.println("The child thread is interrupted.");
			}
			System.out.println("The child thread is finished.");
		}
	}
	
}