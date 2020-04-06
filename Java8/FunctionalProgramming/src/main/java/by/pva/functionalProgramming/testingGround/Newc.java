package by.pva.functionalProgramming.testingGround;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Newc {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str = "Hello"; // Utf-8 is default.
		
		byte[] buffer = str.getBytes(StandardCharsets.UTF_16);

		System.out.println("Buffer length: " +  buffer.length + " bytes");
		
		String s1, s2 = "";
				
		for (int i=0; i<buffer.length; i++) {
			s1 = new String(buffer, 0, buffer.length-i, StandardCharsets.UTF_16);
			s2 = new String(buffer, buffer.length-i, i, StandardCharsets.UTF_16);
			System.out.println(i + " - " + s1 + " + " + s2);
		}
	}
}
