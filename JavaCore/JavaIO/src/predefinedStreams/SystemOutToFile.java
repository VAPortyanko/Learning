package predefinedStreams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SystemOutToFile {
	public static void main(String[] args) throws IOException {
		
		// Save the original PrintStream object. 
		PrintStream originalPrintStream = System.out;
		
		try (PrintStream newOutputStream = new PrintStream(new FileOutputStream("Files/SystemOut.txt", true))){;
			System.setOut(newOutputStream); // Set new value for System.out.
			System.out.println("Hello!");
		}
		
		// Restore the original PrintStream object.
		System.setOut(originalPrintStream);
		
		System.out.println("Hello!");
		
	}
}