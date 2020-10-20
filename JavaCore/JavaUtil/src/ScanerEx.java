import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ScanerEx {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<Integer, String> mp = new LinkedHashMap<Integer, String>();
		mp.put(1, "command 1");
		mp.put(2, "command 2");
		mp.put(3, "command 3");
		mp.put(0, "exit");
		
		MyMenu menu = new MyMenu(mp);
		menu.showMemu();
		System.out.println("choose action ...");
		
		while (sc.hasNextInt()){
			int i = sc.nextInt();
			System.out.println(i);
			if (i> menu.getSize()) 
				System.out.println("Wrong index!");
			else 
				if (i == 0)
					break;
				else
					System.out.println("run the command - " + menu.getCommand(i));
			menu.showMemu();
		}
	System.out.println("Work is finished");
	sc.close();	
	}
}

class MyMenu{

	Map<Integer, String> mp;
	private int size = 0;	
	
	public MyMenu(Map<Integer, String> mp) {
		init(mp);
		size = mp.size()-1;
	}
	
	void init(Map<Integer, String> mp){
		this.mp = mp;
	}
	
	String getCommand(Integer num){
		return mp.get(num);
	}
	
	int getSize(){
		return this.size;
	}
	
	void showMemu(){
		Set<Map.Entry<Integer, String>> mySet =  mp.entrySet();
		for (Map.Entry<Integer, String> set: mySet){
			System.out.print(set.getKey() + " : ");
			System.out.println(set.getValue());
		}
	}
}