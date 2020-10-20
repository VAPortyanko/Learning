package interrupt;

class Sleeper extends Thread {
     
      private int duration;
      public Sleeper(String name, int sleepTime) {
            super(name);
            duration = sleepTime;
            start();
      }
     
      public void run() {
            try {
                   sleep(duration);
            } catch (InterruptedException e) {
                   System.out.println(getName() + " �������");
                   return;
            }
            System.out.println(getName() + " ���������������.");
      }
}

class Joiner implements Runnable {
      private Sleeper sleeper;
      private Thread t;
      public Joiner(String name, Sleeper sleeper) {
            this.sleeper = sleeper;
            t = new Thread(this);
            t.setName(name);
            t.start();
           
      }
     
      public void run() {
            try {
                   sleeper.join();
                   System.out.println(t.getName() + " ��������.");
            } catch (InterruptedException e) {
                   System.out.println(t.getName() + " �������.");
            }
      }
     
      public Thread getThread() {
            return t;
      }
}

public class InterruptExample2 {

    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	  Sleeper sleepy1 = new Sleeper("Sleepy 1", 1500), sleepy2 = new Sleeper("Sleepy 2", 2000);
          Joiner joiner1 = new Joiner("Joiner 1", sleepy1), joiner2 = new Joiner("Joiner 2", sleepy2);
          sleepy1.interrupt();
          joiner2.getThread().interrupt();
      }
}