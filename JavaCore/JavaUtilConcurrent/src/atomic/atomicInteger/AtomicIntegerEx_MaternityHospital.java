package atomic.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerEx_MaternityHospital {

	public static void main(String[] args) {
		
		birth("Иван", true);
		birth("Дмитрий", true);
		birth("Виталий", true);
		birth("Сергей", true);
		
		birth("Аня", false);
		birth("Катя", false);
		birth("Вика", false);
		birth("Юля", false);
		birth("Яна", false);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Поток прерван");
		}

		System.out.println("Всего родилось мальчиков - " + idsFromAtomic.countMan);
		System.out.println("Всего родилось девочек - " + idsFromAtomic.countWoman);
	}
	
	private static void birth(String name, boolean sex){
		NewMan child = new NewMan(name, sex);
		child.start();
	};
}

class NewMan extends Thread{
	
	private String nameChild; // Имя ребенка.
	boolean sex = true;       // Пол ребенка(мужской по умолчанию).
	private String sexString="мальчик";
		
	// Конструктор.
	public NewMan(String name, boolean sex){
		this.nameChild = name;
		this.sex = sex;
		if (sex)
			new idsFromAtomic().addMan();
		else{
			new idsFromAtomic().addWoman();
			this.sexString = "девочка";
		}
	}
	
	@Override
	public void run(){
		
		int timeOut = (int) (Math.random()*4501+500);
		
		System.out.println("Рождается новый человек....");
		try {
			sleep(timeOut);
		} catch (InterruptedException e) {
			System.out.println("Поток прерван");
		}
		
		int hours = Math.round(timeOut/1000);
		int minutes = Math.round((timeOut-hours)/100);
		System.out.println("Родился(ась) " + sexString + " " + nameChild + ". Роды заняли - " + hours + " часа(ов)" + minutes + " минут.");
	 }
}

class idsFromAtomic{
	public static int countMan = 0;           // Количество родившихся мужчин.
	public static int countWoman = 0;         // Количество родившихся женщин.
	
	private static final AtomicInteger sequenceMan = new AtomicInteger();
	private static final AtomicInteger sequenceWoman = new AtomicInteger();
	
	private int nextMan(){
		return sequenceMan.incrementAndGet();
	}
	
	private int nextWoman(){
		return sequenceWoman.incrementAndGet();
	}
	
	public void addMan(){
		idsFromAtomic.countMan = nextMan();
	}
	public void addWoman(){
		idsFromAtomic.countWoman = nextWoman();
	}
}
