package system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class SystemEx {
	public static void main(String[] args) {

/********************************************* arrayCopy *********************************************************************************************************************/
		System.out.println("ArrayCopy:");
		int[] a = {1, 2, 3, 4, 5}, b = new int[10];
		b[0] = 0;
		b[6] = 6;
		b[7] = 7;
		b[8] = 8;
		b[9] = 9;
		for (int val:b)
			System.out.print(val);
				
		System.out.println();
		
		System.arraycopy(a, 0, b, 1, a.length);
				
		for (int val:b)
			System.out.print(val);
				
/********************************************* currentTimeMillis *************************************************************************************************************/
		System.out.println();
		System.out.println();
		System.out.println("Current time:");
		System.out.println(System.currentTimeMillis()); // In milliseconds.
				
/********************************************* gc ***************************************************************************************************************************/
		System.gc();
				
/********************************************* getenv ***********************************************************************************************************************/
		System.out.println();
		System.out.println("Environment settings:");
		
		Map<String, String> env = System.getenv();
		for(Entry<String, String> entry: env.entrySet()){
			System.out.println(entry.getKey() + " - " + entry.getValue());
		} 
				
/********************************************** getProperties ****************************************************************************************************************/
		Properties prop = System.getProperties();
		try {
			prop.storeToXML(new FileOutputStream("d://propertiesToXML.xml"), "comment"); // Write properties list to XML-file.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
/********************************************** getSecurityManager **********************************************************************************************************/
		System.getSecurityManager();
				
/********************************************** setOut **********************************************************************************************************************/
		try {
			System.setOut(new PrintStream("d://out.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		System.out.println("Hello!");
	}
}

