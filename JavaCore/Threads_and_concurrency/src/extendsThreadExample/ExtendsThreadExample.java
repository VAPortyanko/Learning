package extendsThreadExample;

public class ExtendsThreadExample {
	public static void main(String[] args) {
		new NewThred();
		
		try{
			for (int i=10; i>0; i--){
				System.out.println("������� �����:" + i);
				Thread.sleep(300);
			}
		}catch(InterruptedException e){
			System.out.println("������� ����� �������.");
		}
		System.out.println("������� ����� ��������.");
	}
	
	static class NewThred extends Thread{
		public NewThred() {
			super("����-�����.");
			System.out.println("�������� �����" + this);
			start();
		}
		
		public void run(){
			try{
				for (int i=10; i>0; i--){
					System.out.println("�������� �����:" + i);
					Thread.sleep(500);
				}
			}catch(InterruptedException e){
				System.out.println("�������� ����� �������.");
			}
			System.out.println("�������� ����� ��������.");
		}
	}
	
}