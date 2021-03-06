package channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel_Ex {
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile aFile = new RandomAccessFile("Files/Channels/nio-data.txt", "r");
	    FileChannel inChannel = aFile.getChannel();

	    ByteBuffer buf = ByteBuffer.allocate(48);

	    int bytesRead = inChannel.read(buf);
	    while (bytesRead != -1) {

	      System.out.println("Read " + bytesRead + " bytes:");
	      buf.flip();

	      while(buf.hasRemaining()){
	          System.out.print((char) buf.get());
	      }
	      
	      System.out.println("\n");

	      buf.clear();
	      bytesRead = inChannel.read(buf);
	    }

	    aFile.close();
	}
}
