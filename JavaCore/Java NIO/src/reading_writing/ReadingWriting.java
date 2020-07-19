// https://docs.oracle.com/javase/tutorial/essential/io/file.html
package reading_writing;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ReadingWriting {
	public static void main(String[] args) throws IOException {
		
// Commonly used methods for SMALL files.
		System.out.println("*************************************************************************");
		System.out.println("Commonly used methods for SMALL files");
		System.out.println("*************************************************************************");
		Path file1 = Paths.get("Files/ReadingWriting/ReadWriteSmallFiles.txt");
		byte[] fileArray = Files.readAllBytes(file1);
		System.out.println("   The file content:");
		for(byte c: fileArray){
			System.out.print((char) c);
		}
		System.out.println("\n");
		List<String> lines = Files.readAllLines(file1, Charset.forName("UTF-8"));
		System.out.println("   " + lines);
		
		byte[] buf = (System.getProperty("line.separator") + "New line!").getBytes();
		Files.write(file1, buf, StandardOpenOption.APPEND);

// Buffered I/O Methods for Text Files.
		System.out.println("\n*************************************************************************");
		System.out.println("Buffered I/O Methods for Text Files.");
		System.out.println("*************************************************************************");
		
		Path file2 = Paths.get("Files/ReadingWriting/BufferedIO.txt");
		try (BufferedReader reader = Files.newBufferedReader(file2, Charset.forName("UTF-8"))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println("   " + line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

		String s = "New line!";
		try (BufferedWriter writer = Files.newBufferedWriter(file2, Charset.forName("UTF-8"), StandardOpenOption.APPEND)) {
		    writer.newLine();
			writer.write(s, 0, s.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

// Methods for Unbuffered Streams and Interoperable with java.io APIs.
		System.out.println("\n*************************************************************************");
		System.out.println("Methods for Unbuffered Streams and Interoperable with java.io APIs.");
		System.out.println("*************************************************************************");
		
		Path file3 = Paths.get("Files/ReadingWriting/UnbufferedStreams.txt");
		
		try (InputStream in = Files.newInputStream(file3); BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
		
	    byte data[] = (System.getProperty("line.separator") + "Hello world!").getBytes();

	    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(file3, StandardOpenOption.APPEND))) {
	      out.write(data, 0, data.length);
	    } catch (IOException x) {
	      System.err.println(x);
	    }
		
// Methods for Channels and ByteBuffers.
	 	System.out.println("\n*************************************************************************");
	 	System.out.println("Methods for Channels and ByteBuffers.");
	 	System.out.println("*************************************************************************");
	 		
	 	Path file4 = Paths.get("Files/ReadingWriting/ChannelsAndBuffers.txt");
		
 		try (SeekableByteChannel sbc = Files.newByteChannel(file4)) {
 		    ByteBuffer buf2 = ByteBuffer.allocate(10);

 		    String encoding = System.getProperty("file.encoding");
 		    while (sbc.read(buf2) > 0) {
 		        buf2.rewind();
 		        System.out.print(Charset.forName(encoding).decode(buf2));
 		        buf2.flip();
 		    }
 		    
 		} catch (IOException x) {
 		    System.out.println("caught exception: " + x);
 		}		
		
	}
}
