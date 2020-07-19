package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class SimpleTest {

	//public static void main(String[] args) throws IOException {
	// Созадть два потока, которые будут использоать один и тоже RandomAccessFile и параллельно писать по буферу - что будет.
	// Тоже самое но два потока (в каждом свой  RandomAccessFile) и один файл для записи.
	// Попробовать запись  двух режимах rw, rws. 	
	//}
	
	
	// По примеру Read_ write (Input reader + randomAccess File) htfkbpjdfnm token replacer.
	//
	//
	 
	public static void main(String[] args) throws IOException {
	    File f = new File(args[0]);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"), 5); // Use correct encoding here.
	    RandomAccessFile writer = new RandomAccessFile(f, "rw");

	    writer.seek(5);
        writer.write("555".getBytes("UTF-8"));
        String line = reader.readLine();
        System.out.println(line);
	    
	    reader.close();
	    writer.close();
	}
}
