//http://tutorials.jenkov.com/java-exception-handling/exception-handling-templates.html
package closeAndExeptionHandle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CloseTemplate {
	public static void main(String[] args) throws MyException {

		 new InputStreamProcessingTemplate(){
		        public void doProcess(InputStream input) throws IOException{
		            
		        	int inChar = -1;
		            while((inChar = input.read()) != -1){
		                System.out.println((char) inChar);
		            }
		        }
		    }.process("d:/someFile.txt");
		
	}
}

abstract class InputStreamProcessingTemplate {

	public void process(String fileName) throws MyException {
		IOException processException = null;
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);

			doProcess(input);
		} catch (IOException e) {
			processException = e;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					if (processException != null) {
						throw new MyException(processException, e, "Error closing InputStream for file after error processing InputStream for file ..." + fileName);
					} else {
						throw new MyException(e, "Error closing InputStream for file " + fileName);
					}
				}
			}
			if (processException != null) {
				throw new MyException(processException, "Error processing InputStream for file " + fileName);
			}
		}
	}

	// override this method in a subclass, to process the stream.
	public abstract void doProcess(InputStream input) throws IOException;
}

class MyException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyException(Exception processException, String string) {
		super(string, processException);
	}

	public MyException(Exception processException, Exception e, String string) {

		super(string, e);
		this.addSuppressed(processException);
	}
}
