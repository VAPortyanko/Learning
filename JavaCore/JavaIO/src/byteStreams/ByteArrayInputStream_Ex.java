package byteStreams;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStream_Ex {
	public static void main(String[] args) throws IOException {
		
		System.out.println("String = \"There are apples on the tree.\"");
		byte[] buf = "there are apples on the tree.".getBytes();
		ByteArrayInputStream bAIS = new ByteArrayInputStream(buf);
		
		System.out.println("byte available: " + bAIS.available());
		
		System.out.println("Mark support: " + bAIS.markSupported() + "\n");
		
		for (int i=0; i<6; i++)
			System.out.print("[" + (char)bAIS.read() + "]");

		bAIS.mark(0); // 
		System.out.print(" Mark position! ");
		
		for (int i=0; i<6; i++)
			System.out.print("[" + (char)bAIS.read() + "]");
		
		bAIS.reset(); // Recall reset method.
		System.out.print(" Reset to mark position! ");		
		
		for (int i=0; i<6; i++)
			System.out.print("[" + (char)bAIS.read() + "]");

		bAIS.close();
	}
}