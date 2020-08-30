package predefinedStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleRead {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Print text. Press \"!stop\" to exit.");

		String str;
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			char firstChar = (char) br.read(); // Read first symbol.
			str = br.readLine(); // Read the rest.
			System.out.println("First symbol: " + firstChar + ". String: " + str);
			if (str.equalsIgnoreCase("stop"))
				break;
		}
	}
}
