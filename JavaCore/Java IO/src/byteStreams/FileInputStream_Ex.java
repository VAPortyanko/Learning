package byteStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStream_Ex {
	public static void main(String[] args) {
		
		File f = new File("Files/FileInputStream/FileForRead.txt");
		FileInputStream fileStream = null;
		
		// create the stream.
		try {
			fileStream = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			int size = fileStream.available();
			System.out.println("Available bytes: " + size);
			System.out.println("Mark suppor: " + fileStream.markSupported());
			
			for (int i = 0; i<(size/2); i++){
				System.out.print((char) fileStream.read());
			}
			
			size = fileStream.available();
					
			System.out.println();
			System.out.println("Available bytes: " + size);
			
			// skip all bytes except the last 8s.
			fileStream.skip(size-8);
			
			size = fileStream.available();
			System.out.println("Available bytes: " + size);
			
			// read the last 8 bytes in the byte array.
			byte[] massB = new byte[size];
			fileStream.read(massB);
			
			System.out.println(new String(massB));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally{
			try {
				fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
