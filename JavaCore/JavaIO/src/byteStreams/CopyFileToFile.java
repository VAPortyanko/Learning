package byteStreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileToFile {
	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		
		
		try {
			fileIn = new FileInputStream("Files/FileA.txt");
			fileOut = new FileOutputStream("Files/FileB.txt");
		} catch (FileNotFoundException e1) {
			System.out.println("File not found!");
			throw e1;
		}
		
		
		try {	
			int c = -1;
			while (true){
				try {
					c = fileIn.read();
				} catch (IOException e) {
					System.out.println("IOexception!");
				}
				
				try {
					if (c == -1)
						break;
					else 	
						fileOut.write(c);
				} catch (IOException e) {
					System.out.println("IOexception!");
				}
			}
			
			String additionalString = " (Copied from FileA.txt)";	
			fileOut.write(additionalString.getBytes());
		} catch (IOException e) {
			System.out.println("IOexception!");
		} finally{
			try {
				fileIn.close();
				fileOut.close();	
			} catch (IOException e) {
				System.out.println("CloseException!");
			}
		}
		
		System.out.println("Copy complete!");
	}
}