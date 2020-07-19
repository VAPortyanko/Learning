// https://docs.oracle.com/javase/tutorial/essential/io/rafs.html
package reading_writing;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RandomAccessWithChanel {

	public static void main(String[] args) {
		
		ByteBuffer out = ByteBuffer.wrap("I was here!\n".getBytes(Charset.defaultCharset()));
		ByteBuffer copy = ByteBuffer.allocate(12);
		
		Path path = Paths.get("Files/RandomAccessFile/RandomAccessFile.txt");

		try (FileChannel fc = (FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE))) {
			// Read the first 12
			// bytes of the file.
			int nread;
			do {
				nread = fc.read(copy);
			} while (nread != -1 && copy.hasRemaining());

// System.out.println(new String(copy.array(), Charset.defaultCharset()));			
			
			// Write "I was here!" at the beginning of the file.
			fc.position(0);
			while (out.hasRemaining())
				fc.write(out);
			out.rewind();

			// Move to the end of the file. Copy the first 12 bytes to
			// the end of the file. Then write "I was here!" again.
			long length = fc.size();
			fc.position(length - 1);
			copy.flip();

			while (copy.hasRemaining())
				fc.write(copy);
			
			while (out.hasRemaining())
				fc.write(out);
			
		} catch (IOException x) { 
			System.out.println("I/O Exception: " + x);
		}
	}
}
