package test;

import java.io.*;

public class TTTTTTTTTT {
	public static void main(String[] args) throws IOException {
		InputStream reader = new ByteArrayInputStream("Hello!".getBytes());
		BufferedInputStream bis = new BufferedInputStream(reader, 5);
		bis.mark(4);
		System.out.println((char)bis.read());
		System.out.println((char)bis.read());
		System.out.println((char)bis.read());
		System.out.println((char)bis.read());
		System.out.println((char)bis.read());

		System.out.println(bis.available());
		
		bis.reset();
		
		System.out.println((char) bis.read());
		

	}
}
