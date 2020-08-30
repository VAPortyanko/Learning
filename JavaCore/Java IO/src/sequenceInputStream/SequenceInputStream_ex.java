package sequenceInputStream;

import java.io.*;

public class SequenceInputStream_ex {
	public static void main(String args[]) {

		try (FileInputStream fin1 = new FileInputStream("Files/SequenceInputStream/testin1.txt");
				FileInputStream fin2 = new FileInputStream("Files/SequenceInputStream/testin2.txt");
				FileOutputStream fout = new FileOutputStream("Files/SequenceInputStream/testout.txt");
				SequenceInputStream sis = new SequenceInputStream(fin1, fin2);) {

			int i;
			while ((i = sis.read()) != -1) {
				fout.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Success..");
	}
}