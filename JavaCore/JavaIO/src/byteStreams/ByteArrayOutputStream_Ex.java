package byteStreams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayOutputStream_Ex {
	public static void main(String[] args) throws IOException {

		ByteArrayOutputStream bAOS = new ByteArrayOutputStream(50); // Size = 50 bytes.

		bAOS.write(5);
		bAOS.write(4);
		bAOS.write(3);
		bAOS.write(2);

		byte[] mb = bAOS.toByteArray();

		for (byte f : mb) {
			System.out.print(f);
		}

		bAOS.close();
	}
}
