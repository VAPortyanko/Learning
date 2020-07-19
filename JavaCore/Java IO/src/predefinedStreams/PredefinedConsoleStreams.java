package predefinedStreams;

import java.io.IOException;

public class PredefinedConsoleStreams {

	public static void main(String[] args) {
		// System.in  - InputStream.
		// System.out - PrintStream.
		// System.err - PrintStream.

		System.out.println("System.out.println");
		System.err.println("System.err.println");
		
		try {
			System.out.println("Input symbol:");
			int readedByte = System.in.read();
			System.out.println("Read byte input: " + readedByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
