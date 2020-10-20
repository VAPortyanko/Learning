package synchronizers.semaphore;

import java.util.concurrent.Semaphore;
// поток может выполнять код ограниченный semaphore.ecquire и semaphore.release(для одного и того же semaphore)
// захватив этот семафор при наличии свободных разрешений.

public class Semaphore_SemaphoreMetods {
	// Создаем объект семафора.
	static Semaphore semaphore = new Semaphore(6); // работают одновременно 6 потока(либо 1 поток которые захвати все 5 разрешений).
	
	public static void main(String[] args) throws InterruptedException {
		Semaphore_SemaphoreMetods obj1 = new Semaphore_SemaphoreMetods();
		obj1.semaphoreMetods(semaphore);
	}
	
	void semaphoreMetods(Semaphore semaphore) throws InterruptedException{
		System.out.println("Получение доступных разрешений для семафора. Их текущее количество - " + semaphore.availablePermits());
		System.out.println();
		
		System.out.println("Являеться ли семафор справедливым? - " + semaphore.isFair() + ".(Справедливый семафор - потоки, которые уже получали семафор становять в конец очереди, иначе они могуи посторно захватить его даже если в очереди еще много ожидающих)");
		System.out.println();
		
		semaphore.acquire(); // захват одного разрешения семафора (Метод может порождать исключение)
		System.out.println("Захвачено 1 разрешение семафора с помощью метода semaphore.acquire()");
		System.out.println("Получение доступных разрешений для семафора. Их текущее количество(после захвата одного) - " + semaphore.availablePermits());
		System.out.println();
		
		semaphore.acquire(3); // Захват сразу трех разрешений.
		System.out.println("Захвачено 3 разрешение семафора с помощью метода semaphore.acquire(3)");
		System.out.println("Получение доступных разрешений для семафора. Их текущее количество(после захвата трех) - " + semaphore.availablePermits());
		System.out.println();
		
		int acquireCount = semaphore.drainPermits(); // Захват всех свободных разрешений.
		System.out.println("Захвачены все свободные разрешения семафора в количестве - " + acquireCount + " с помощью метода semaphore.drainPermits()");
		System.out.println("Получение доступных разрешений для семафора. Их текущее количество(после захвата всех осташихся свободных) - " + semaphore.availablePermits());
		System.out.println();
		
		int waitingCount = semaphore.getQueueLength();
		System.out.println("Получение количества потоков(!не запросов) ожидающих семафор через команду semaphore.getQueueLength()");
		System.out.println("Число ожидающих запросов на захват семафора - " + waitingCount);
		System.out.println();
		
		System.out.println("Создаем параллельный поток, в котором проверяем количество ожидающих семафора потоков. Поток наичнает работу с задеожкой, поэтому главный поток успевает запросит еще 3 разрешения");
		NewThread newThreed = new NewThread(semaphore);
		newThreed.start();
		System.out.println();
		
		System.out.println("Пробуем захватить еще 3 разрешение и заново проверяем число ожидающих потоков");
		semaphore.acquire(3); // Тут мы висим, так как все разрешения семафора заняты. Висим до тех пор, пока паралелльный поток, например, не освободит 1 или несколько разрешений(даже если он их не занимал).
		System.out.println("Продолжение работы!"); // параллельный поток освободил 4 разрешения(работа продолжена + одно разрешение свободно).
		System.out.println();
		
		
		System.out.println("Получение доступных разрешений для семафора. Их текущее количество - " + semaphore.availablePermits());
		System.out.println("Захват свободного разрешения");
		semaphore.acquire();
		System.out.println("Получение доступных разрешений для семафора. Их текущее количество - " + semaphore.availablePermits());
		System.out.println();
		
		System.out.println("Все доступные разрешения исчерпаны. Пробуем команду tryAcuire()");
		semaphore.tryAcquire();
		System.out.println("Попытка захватить разрешение не удалсь(так как свободных нет) следовательно команда пропускается");
	}
}

class NewThread extends Thread{
	Semaphore semaphore;
	
	public NewThread(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	
	@Override
	public void run(){
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("(Параллельный поток №1)Число потоков запросивших 1 или несколько разрешений, которые находяться в очереди на ожидании - " + semaphore.getQueueLength());
		
		System.out.println("(Параллельный поток №1) Есть ли ожидающие потоки? - " + semaphore.hasQueuedThreads());
		semaphore.release(4); // Для продолжения работы в методе main после предпоследней команды, необходимо освободить миним 3 разрешения, так как main ждет захвата 3 разрешений.
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("(Параллельный поток №1) Есть ли ожидающие потоки? - " + semaphore.hasQueuedThreads());
	}
}
