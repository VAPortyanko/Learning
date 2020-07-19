package byteStreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream_Ex {
	public static void main(String[] args) throws IOException {
				
		FileOutputStream fileOut = null;
		
		try {
		
			fileOut = new FileOutputStream("Files/FileOutputStream/FileForWrite.txt");

			String str = "Stroka la la la\r\n";
			
			byte[] b = str.getBytes(); 
			fileOut.write(b);
			
			fileOut.write(new byte[]{100, 101, 102, 103});
			
			fileOut.write('\r');
			fileOut.write('\n');
			
			fileOut.write('H');
			fileOut.write('e');
			fileOut.write('l');
			fileOut.write('l');
			fileOut.write('o');
			fileOut.write('!');

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			fileOut.close();
		}
		
		System.out.println("File is Closed!");
	}
}

