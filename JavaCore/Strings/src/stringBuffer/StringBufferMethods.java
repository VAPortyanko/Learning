package stringBuffer;

public class StringBufferMethods {
	public static void main(String[] args) {
		
		StringBuffer str = new StringBuffer();
		
		System.out.println("Start string length " + str.length());
		System.out.println("Start buffer size " + str.capacity() + " symbols");
		
		int capacity = 0;
		// when a string reaches a length equal to the size of the buffer, it doubles and 2 is added.
		for (int i = 1; i<=100; i++){
			System.out.println("String length " + str.append(0).length());
			if (str.capacity() != capacity)
				System.out.println("buffer size " + str.capacity() + " symbols");
		}		
		
		System.out.println("-------------------delete all from the string---------------------");
		str.delete(0, str.length()+1);
		
		System.out.println("string length after deletion " + str.length());
		System.out.println("buffer size after deleteion " + str.capacity() + " symbols");
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("add new string");
		str.append("123456789");
		System.out.println(str);
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("set length to 20 symbols");
		str.setLength(20);
		// System.out.println((int)str.charAt(22));                  java.lang.StringIndexOutOfBoundsException
		// System.out.println((int)str.charAt(0));                   1
		// System.out.println((int)str.charAt(15));                  0
		System.out.println(str);
		System.out.println("string length " + str.length());
		System.out.println("buffer size " + str.capacity() + " symbols");
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("set length to 5 symbols");
		str.setLength(5);
		System.out.println(str);
		System.out.println("string length " + str.length());
		System.out.println("buffer size " + str.capacity() + " symbols");
	
		System.out.println("---------------------------------------------------------------------");
		System.out.println("use method TrimToSize");
		str.trimToSize();
		System.out.println(str);
		System.out.println("string length " + str.length());
		System.out.println("buffer size " + str.capacity() + " symbols");
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("use method ensureCapacity(7)");
		str.ensureCapacity(7);// 7 is minimal size for the buffer. as (5)*2+2 < 7 then new buffer size is 12.
		System.out.println(str);
		System.out.println("string length " + str.length());
		System.out.println("buffer size " + str.capacity() + " symbols");
		
	}
}
