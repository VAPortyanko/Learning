// https://devcolibri.com/%D1%83%D1%80%D0%BE%D0%BA-4-randomaccessfile-%D0%B8-%D0%B5%D0%B3%D0%BE-%D0%B2%D0%BE%D0%B7%D0%BC%D0%BE%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D0%B8/
package randomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFile_Ex1 {

    private static WorkWithFile worker;

    public static void main(String[] args) throws IOException {

        worker = new WorkWithFile("Files/RandomAccessFile/testfile.txt");

        worker.write("Give me a break from your take and your take. \n" +
                "Come on and give me a break,\n" +
                "what do you want from me?\n" +
                "Feeding the rich with that son of a bitch.\n" +
                "Well that son of a bitch,\n" +
                "he looks just like me!\n" +
                "Yeah, yeah");

        System.out.println(worker.goTo(100));
        System.out.println("*******");

        System.out.println(worker.read());
        System.out.println("*******");

        System.out.println(worker.readFrom(105));
    }
}





class WorkWithFile {

    private String path;
    private RandomAccessFile file;

    public WorkWithFile(String path) {
        this.path = path;
    }

    public long goTo(int num) throws IOException {

        file = new RandomAccessFile(path, "r");
        file.seek(num);
        long pointer = file.getFilePointer();
        file.close();

        return pointer;
    }

    public String read() throws IOException {
    	
        file = new RandomAccessFile(path, "r");
        String res = "";
        int b = file.read();

        while(b != -1){
            res = res + (char)b;
            b = file.read();
        }
        
        file.close();

        return res;
    }


    public String readFrom(int numberSymbol) throws IOException {

        file = new RandomAccessFile(path, "r");
        String res = "";

        file.seek(numberSymbol);
        int b = file.read();

        while(b != -1){
            res = res + (char)b;
            b = file.read();
        }

        file.close();

        return res;
    }


    public void write(String st) throws IOException {
    	
        file = new RandomAccessFile(path, "rw");
        file.write(st.getBytes());
        file.close();
    }
}


