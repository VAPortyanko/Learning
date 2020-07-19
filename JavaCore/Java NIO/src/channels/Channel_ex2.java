package channels;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Channel_ex2 {

	public static void main(String[] args) {

		try (FileInputStream in = new FileInputStream("Files/Channels/Channel_text.txt")) {

			FileChannel channel = in.getChannel();
			System.out.println("Is channel open?: " + channel.isOpen());
			System.out.println("Channel's data size is " + channel.size() + " bytes");
			System.out.println("Position: " + channel.position());

			ByteBuffer buffer = ByteBuffer.allocate(260);
			
			int bytesRead;
			while ((bytesRead = channel.read(buffer)) != -1) {
				
				System.out.println("\n     " + bytesRead + " bytes have been read!");
				System.out.println("     Current buffer position is " + buffer.position() + ". Invoke flip() method!");
				buffer.flip();
				System.out.println("     Current buffer position is " + buffer.position() + ". Read data from buffer:\n");
								
				Charset charset = Charset.forName("UTF-8");
				CharBuffer charbuff = charset.decode(buffer);
				
				while (charbuff.hasRemaining()) {
					System.out.print(charbuff.get());
				}
				
				System.out.println();
				
				buffer.clear();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
