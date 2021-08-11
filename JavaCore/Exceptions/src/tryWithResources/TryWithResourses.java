package tryWithResources;

import java.io.ByteArrayInputStream;

public class TryWithResourses {

	public static void main(String[] args) throws Exception {
		
		int i = readFirstLineFromFile();
		System.out.println(i);
	}
	
	static int readFirstLineFromFile() throws Exception{

		byte[] buf = {1,2,3,4,5,6,7,8,9,0};	
		try (ByteArrayInputStream bais = new ByteArrayInputStream(buf)) {
			System.out.println("After next command stream will be closed automaticaly!");
			return bais.read();
		} // After that, the stream will be closed automatically.
	}
}
/* Try with resources supports Suppressed exceptions. */

