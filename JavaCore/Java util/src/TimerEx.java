import java.util.Timer;
import java.util.TimerTask;

public class TimerEx {
	public static void main(String[] args) {
		Timer myTimer = new Timer();
		NewTask tsk = new NewTask();  
		System.out.println("Execute a task every 1 seconds:");
		myTimer.schedule(tsk, 0, 1000);
		
	}
}

class NewTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("Hello world!");
	}
}