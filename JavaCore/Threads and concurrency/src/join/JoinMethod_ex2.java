package join;

public class JoinMethod_ex2 implements Runnable{

	String name;
	Thread t;
	int count;
	
	public JoinMethod_ex2(String name, int count) {
		this.name = name;
		this.count = count;
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		for (int i = count; i > 0; i--)
		System.out.println("Hello, my name is " + name);
		System.out.println("The thread '" + Thread.currentThread().getName() + "' is finished.");
	}
	
	public Thread getThread(){
		return t;
	}
	
	public static void main(String[] args){
		JoinMethod_ex2 t1 = new JoinMethod_ex2("Olia", 5);
		JoinMethod_ex2 t2 = new JoinMethod_ex2("Dima", 5);
		JoinMethod_ex2 t3 = new JoinMethod_ex2("Slava", 5);
		
		// Block try guarantee that main thread will finish last.
		try {
			t1.getThread().join();// Join the thread 1 and wait for its completion.
			t2.getThread().join();// not execute while t1 is running.
			t3.getThread().join();// not execute while t2 is running.
			/*We will can contiue execute comands when t1, t2 and t3 threads ended only*/
		} catch (InterruptedException e) {
			System.out.println("The main thread is interrupted.");
		}

		
		System.out.println("The thread '" + Thread.currentThread().getName() + "' is finished.");
	}
}