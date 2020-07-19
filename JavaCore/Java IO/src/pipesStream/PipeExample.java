package pipesStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        final PipedOutputStream output = new PipedOutputStream();
		final PipedInputStream  input  = new PipedInputStream(output);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	for (int i=0; i<100;i++) {
                		System.out.println("Write " + i+":");
                		output.write("Hello world!".getBytes());
                	}
                	output.close();  // PipedOutputStream is closed. So, when all data will be read from "input", the next call input.read() returns -1.
                	                 // But if we omit "output.close()" and try to call input.read() then exception will be thrown.
                } catch (Exception e) {
                	System.out.println(e);
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while(data != -1){
                        System.out.print((char) data);
                        data = input.read();
                    }
                } catch (IOException e) {
                	System.out.println(e);
				}
            }
        });

        thread1.start();
        thread2.start();
        
        thread2.join();
        input.close();
        
    }
}