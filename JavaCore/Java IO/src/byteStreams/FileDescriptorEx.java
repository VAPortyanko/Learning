// https://stackoverflow.com/questions/34980241/why-closing-an-input-stream-closes-the-associated-file-descriptor-as-well-even

package byteStreams;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDescriptorEx {

	public static void main(String[] args) {
		
		File file = new File("Files/FileInputStream/FileDescriptorEx.txt");
		
		FileOutputStream out1 = null, out2 = null, out3 = null;
		
		try {
			 out1 = new FileOutputStream(file);
			
			FileDescriptor fd = out1.getFD();
			
			out2 = new FileOutputStream(fd);
			
			out1.write("Stream1 output! \n\t".getBytes());
			out2.write("Stream2 output! \n".getBytes());
			
			out3 = new FileOutputStream(FileDescriptor.out);
			
			out3.write("Stream3 output! \n".getBytes());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out1.close();
				// out2.close(); As out1 and out2 share the same FileDescriptor the closure of the "out1" leads to the closing of the "out2".
				out3.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
