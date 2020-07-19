package runtime;

import java.io.IOException;

public class RunProcess {
	public static void main(String[] args) {
		
		Runtime r = Runtime.getRuntime();
		
		Process p = null;
		
		try {
			p = r.exec("mspaint");
			System.out.println("Wait for process ending ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Exit value: " + p.waitFor());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

