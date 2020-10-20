package autoClosable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class TryWithResources_Ex {

	public static void main(String[] args) throws MalformedURLException, IOException {
		TryWithResources_Ex.printFileJava7("Files/TryWithResources.txt");
	}

	private static void printFileJava7(String fileName) throws IOException {

		try (FileInputStream input = new FileInputStream(fileName);
			BufferedInputStream bufferedInput = new BufferedInputStream(input)) {

			int data = bufferedInput.read();
			while (data != -1) {
				System.out.print((char) data);
				data = bufferedInput.read();
			}
		}
	}
}