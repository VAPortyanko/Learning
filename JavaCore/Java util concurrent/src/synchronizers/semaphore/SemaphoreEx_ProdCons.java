package synchronizers.semaphore;

// Реализация поставщика и потребителя, использующая
// семафоры для управления синхронизацией
 
import java.util.concurrent.Semaphore;
 
class Q {
 
    int n;
 
    // начать с недоступного семафора потребителя
 
    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);
 
    void get() {
        try {
            semCon.acquire();
        } catch(InterruptedException e) {
            System.out.println("Перехвачено исключение типа InterruptedException");
        }
        System.out.println("Получено: " + n);
        semProd.release();
    }
 
    void put(int n) {
        try {
            semProd.acquire();
        } catch(InterruptedException e) {
            System.out.println("Перехвачено исключение типа InterruptedException");
        }
        this.n = n;
        System.out.println("Отправлено: " + n);
        semCon.release();
    }
}
 
class Producer implements Runnable {
    Q q;
 
    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }
 
    public void run() {
        for(int i = 0; i < 7; i++) {
            q.put(i);
        }
    }
}
 
class Consumer implements Runnable {
    Q q;
 
    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
 
    public void run() {
        for(int i = 0; i < 7; i++) {
            q.get();
        }
    }
}
 
class SemaphoreEx_ProdCons {
    public static void main(String args[]) {
        Q q = new Q();
        new Consumer(q);
        new Producer(q);
    }
}