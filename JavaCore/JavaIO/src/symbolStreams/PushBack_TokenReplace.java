// http://tutorials.jenkov.com/java-howto/replace-strings-in-streams-arrays-files.html
package symbolStreams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

public class PushBack_TokenReplace extends Reader {

	protected PushbackReader pushbackReader = null;
	protected ITokenResolver tokenResolver = null;
	protected StringBuilder tokenNameBuffer = new StringBuilder();
	protected String tokenValue = null;
	protected int tokenValueIndex = 0;
	
	public static void main(String[] args) throws FileNotFoundException {

		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("impl1", "ByteBuffer");
		tokens.put("impl2", "CharBuffer");
		//tokens.put("impl3", "DoubleBuffer");
		tokens.put("impl4", ""); //tokens.put("impl4", "FloatBuffer");
		tokens.put("impl5", "IntBuffer");
		tokens.put("impl6", "LongBuffer");
		tokens.put("impl7", "ShortBuffer");

		MapTokenResolver resolver = new MapTokenResolver(tokens);

		try (Reader source = new FileReader("Files/PushBack/PushBackFile_in.txt");
			 Reader reader = new PushBack_TokenReplace(source, resolver);) {
			int data = reader.read();
			while (data != -1) {
				System.out.print((char) data);
				data = reader.read();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public PushBack_TokenReplace(Reader source, ITokenResolver resolver) {
		this.pushbackReader = new PushbackReader(source, 2);
		this.tokenResolver = resolver;
	}

	public int read(CharBuffer target) throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}

	public int read() throws IOException {
		if (this.tokenValue != null) {
			if (this.tokenValueIndex < this.tokenValue.length()) {
				return this.tokenValue.charAt(this.tokenValueIndex++);
			}
			if (this.tokenValueIndex == this.tokenValue.length()) {
				this.tokenValue = null;
				this.tokenValueIndex = 0;
			}
		}

		int data = this.pushbackReader.read();
		if (data != '$')
			return data;

		data = this.pushbackReader.read();
		if (data != '{') {
			this.pushbackReader.unread(data);
			return '$';
		}
		this.tokenNameBuffer.delete(0, this.tokenNameBuffer.length());

		data = this.pushbackReader.read();
		while (data != '}') {
			this.tokenNameBuffer.append((char) data);
			data = this.pushbackReader.read();
		}

		this.tokenValue = this.tokenResolver.resolveToken(this.tokenNameBuffer.toString());

		if (this.tokenValue == null) {
			this.tokenValue = "${" + this.tokenNameBuffer.toString() + "}";
		}
		if (this.tokenValue.length() == 0) {
			return read();
		}
		return this.tokenValue.charAt(this.tokenValueIndex++);

	}

	public int read(char cbuf[]) throws IOException {
		return read(cbuf, 0, cbuf.length);
	}

	public int read(char cbuf[], int off, int len) throws IOException {
		int charsRead = 0;
		for (int i = 0; i < len; i++) {
			int nextChar = read();
			if (nextChar == -1) {
				if (charsRead == 0) {
					charsRead = -1;
				}
				break;
			}
			charsRead = i + 1;
			cbuf[off + i] = (char) nextChar;
		}
		return charsRead;
	}

	public void close() throws IOException {
		this.pushbackReader.close();
	}

	public long skip(long n) throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}

	public boolean ready() throws IOException {
		return this.pushbackReader.ready();
	}

	public boolean markSupported() {
		return false;
	}

	public void mark(int readAheadLimit) throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}

	public void reset() throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}
}

interface ITokenResolver {
	public String resolveToken(String tokenName);
}

class MapTokenResolver implements ITokenResolver {

	protected Map<String, String> tokenMap = new HashMap<String, String>();

	public MapTokenResolver(Map<String, String> tokenMap) {
		this.tokenMap = tokenMap;
	}

	public String resolveToken(String tokenName) {
		return this.tokenMap.get(tokenName);
	}

}
