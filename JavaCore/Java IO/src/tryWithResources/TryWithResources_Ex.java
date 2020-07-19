package tryWithResources;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class TryWithResources_Ex {

	public static void main(String[] args) throws MalformedURLException, IOException {
		TryWithResources_Ex.printFileJava7("Files/TryWithResources.txt");
	}

	private static void printFileJava7(String fileName) throws IOException {

		try (FileInputStream input = new FileInputStream(fileName); // A resource must be both declared and initialized inside the try.
			BufferedInputStream bufferedInput = new BufferedInputStream(input)) {

			int data = bufferedInput.read();
			while (data != -1) {
				System.out.print((char) data);
				data = bufferedInput.read();
			}
		}
	}
}

// Multiple resources can be declared just fine in a try-with-resources block by separating them with a semicolon:
//
// try (Scanner scanner = new Scanner(new File("testRead.txt"));
//      PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
//      while (scanner.hasNext()) {
//         writer.print(scanner.nextLine());
//      }
// }